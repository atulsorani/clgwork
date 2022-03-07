package com.bca2021.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.m_lst);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //Uri uri = Uri.parse("content://com.sqlite.bca2021");

        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + "='Amit'";

        ContentResolver resolver = getContentResolver();
        try {
            Cursor res = resolver.query(uri,
                    projection,
                    selection,
                    null,
                    null);
//            Cursor res = resolver.query(uri,
//                    null,
//                    null,
//                    null,
//                    null);

            arrayList = new ArrayList<>();

            if (res.getCount() == 0) {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, res.getCount()+"", Toast.LENGTH_SHORT).show();
                while (res.moveToNext()) {
                    arrayList.add(res.getString(0) + ":" + res.getString(1));
                    arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                    lst.setBackgroundColor(Color.rgb(45,56,34));
                    lst.setAdapter(arrayAdapter);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}