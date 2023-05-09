package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PDFselect extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfselect);

        listView = findViewById(R.id.listView);

        int[] imageID = {R.drawable.medicalinvoices, R.drawable.healthcheck, R.drawable.medicalrecord};
        String[] name  = {"Medical Invoices","Medical Reports", "Other Documents"};

        ArrayList<ListClass> classArrayList = new ArrayList<>();

        for(int i=0 ;  i<3 ;  i++)
        {
            ListClass listelement = new ListClass( name[i],imageID[i]);
            classArrayList.add(listelement);
        }

        CustomListAdapter listAdapter = new CustomListAdapter(PDFselect.this, classArrayList);

        //Log.d("QWERTY", String.valueOf(classArrayList));

        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PDFselect.this, RetrievePDFActivity.class);
                intent.putExtra("TypeofDoc", name[i]);
                startActivity(intent);
            }

        });





    }
}