package com.example.pethoalpar.zxingexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class ekleme extends AppCompatActivity {
    private EditText etName, etBarcode, etPro = null;
    private String strName, strBarcode,strPro = null;
    private Button button;
    final Context context = this;
    private    DBHelper db;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekleme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etName = (EditText) findViewById(R.id.editText);
        etBarcode = (EditText) findViewById(R.id.editText2);
        etPro = (EditText) findViewById(R.id.editText1);
        button = (Button) this.findViewById(R.id.button3);
        db= new DBHelper(this);
        // geçiçi veri ekleme
     /*   Log.d("Insert: ", "Inserting ..");
        db.addContact(new ILAC("Parol", "9100000000", "9786059866941"));
        db.addContact(new ILAC("Srinivas", "9199999999", "9786059866942"));
        db.addContact(new ILAC("Tommy", "9522222222", "9786059866943"));
        db.addContact(new ILAC("Karthik", "9533333333", "9786059866944"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<ILAC> contacts = db.getAllContacts();

        for (ILAC cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber() + ",Barcode";
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Insert: ", "Inserting ..");
                strName = etName.getText().toString().trim();
                strPro=etPro.getText().toString().trim();
                strBarcode=etBarcode.getText().toString().trim();
                db.addContact(new ILAC(strName, strPro, strBarcode));
                Toast.makeText(context, "Ekleme İşlemi Tamamlandı", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                etBarcode.setText(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    }


