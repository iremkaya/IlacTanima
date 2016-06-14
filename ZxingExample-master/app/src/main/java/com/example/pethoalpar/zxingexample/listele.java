package com.example.pethoalpar.zxingexample;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class listele extends AppCompatActivity  {
    TextToSpeech tts;
    private TextView tvName, tvProspektüs,tvBarcode;
    private Bundle extras = null;
    private String strName, strTC = null;
    private int MY_DATA_CHECK_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listele);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvName = (TextView) findViewById(R.id.textView);
        tvProspektüs = (TextView) findViewById(R.id.textView2);
        tvBarcode = (TextView) findViewById(R.id.textView3);
        extras = getIntent().getExtras();
        strName = extras.getString(MainActivity.BARCODE);
        DBHelper db = new DBHelper(this);
        ILAC ılc = db.getContact(strName);
        if(ılc!=null){
            tvName.setText(ılc.getName());
            tvProspektüs.setText(ılc.getPhoneNumber());
            tvBarcode.setText(strName);
        }
        tts=new TextToSpeech(listele.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("tr", "TR");
                    int result = tts.setLanguage(locale);
                    //int result=tts.setLanguage(Locale.ENGLISH);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        String text ;
        if(tvName.getText().toString()=="")
            text="İlaç Tanımsız";
        else
            text= tvName.getText().toString() +"  "+tvProspektüs.getText().toString() ;
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
