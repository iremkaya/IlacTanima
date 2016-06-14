package com.example.pethoalpar.zxingexample;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GSTER extends AppCompatActivity {
    DBHelper db;//veritabanimizin adi
    public static int idSnf;//
    public static String NAME = "name";
    public static String BARCODE  = "barcode";
    public static String ID="ıd";
    public static String PRO   = "pro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gster);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelper(this);
        Log.d("Reading: ", "Reading all contacts..");
        List<ILAC> contacts = db.getAllContacts();
        final ListView listemiz=(ListView) findViewById(R.id.listView1);
        final ArrayList<String> txt = new ArrayList<String>();//array list
        final ArrayList<String> txt2 = new ArrayList<String>();//array list
        final ArrayList<Integer> idtxt = new ArrayList<Integer>();//array list
            int i = 1;
        for (ILAC cn : contacts) { //döngü ile veritabanindan çekilen kolonlarin degiskenlere aktarilmasi
                String k_adi = cn.getName();
                int id = cn.getID();
                String sifre =cn.getPhoneNumber();
                String k_yeri = cn.getBarcode();
                idtxt.add((int)id);//array liste bunlarin aktarilmasi
                txt.add(i+"),"+id+","+k_adi+ ","+ sifre+ ","+k_yeri);//arrayliste aktarim yapilmasi
                txt2.add(i+") "+k_adi+ "  "+ sifre+ "  "+k_yeri);//arrayliste aktarim yapilmasi
                i++;

            }

            ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, android.R.id.text1, txt2);

            listemiz.setAdapter(veriAdaptoru);// listview e gerekli bilgilerin aktarilmasi

            listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {//listview in click metodu
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {

                    idSnf = idtxt.get(arg2);//seçilenin ID degerini integer degiskenine atariz.
                   // Toast.makeText(getApplicationContext(), txt.get(arg2), Toast.LENGTH_LONG).show();
                    String ad=txt.get(arg2);
                    List<String> eleman = Arrays.asList(ad.split(","));
                    Bundle extras = new Bundle();
                    extras.putString(ID, eleman.get(1));
                    extras.putString(NAME, eleman.get(2));
                    extras.putString(PRO, eleman.get(3));
                    extras.putString(BARCODE, eleman.get(4));
                    Intent intent = new Intent();
                    intent.putExtras(extras);
                    intent.setClass(getApplicationContext(), update.class);
                    startActivity(intent);
                }
            });


        }
    }


