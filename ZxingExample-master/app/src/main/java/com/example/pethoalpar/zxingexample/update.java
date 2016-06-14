package com.example.pethoalpar.zxingexample;

        import android.content.Context;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
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

public class update extends AppCompatActivity {
    private EditText etName, etBarcode, etPro ,tvID = null;
    private String strName, strBarcode,strPro= null;
    private int strID;
    private Button button,button1;
    final Context context = this;
    private    DBHelper db;
    private Bundle extras = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db= new DBHelper(this);
        etName = (EditText) findViewById(R.id.editText);
        etBarcode = (EditText) findViewById(R.id.editText2);
        etPro = (EditText) findViewById(R.id.editText1);
        tvID = (EditText) findViewById(R.id.editText7);
        button = (Button) this.findViewById(R.id.button3);
        button1 = (Button) this.findViewById(R.id.button5); // silme
        extras = getIntent().getExtras();
        etName.setText(extras.getString(GSTER.NAME));
        etBarcode.setText(extras.getString(GSTER.BARCODE));
        etPro.setText(extras.getString(GSTER.PRO));
        tvID.setText(extras.getString(GSTER.ID));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Insert: ", "Inserting ..");
                strName = etName.getText().toString().trim();
                strPro = etPro.getText().toString().trim();
                strBarcode = etBarcode.getText().toString().trim();
                strID=Integer.parseInt(tvID.getText().toString());
                db.updateContact(new ILAC(strID, strName, strPro, strBarcode));
                Toast.makeText(context, "Düzenleme İşlemi Tamamlandı", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), GSTER.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Insert: ", "Inserting ..");
                strName = etName.getText().toString().trim();
                strPro = etPro.getText().toString().trim();
                strBarcode = etBarcode.getText().toString().trim();
                strID=Integer.parseInt(extras.getString(GSTER.ID));
                ILAC ıc =db.getContactID(strID);
                db.deleteContact(ıc);
                Toast.makeText(getApplicationContext(), "Kayit Silindi" , Toast.LENGTH_LONG).show();//uyari mesaji
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), GSTER.class);
                startActivity(intent);
            }
        });
    }

}
