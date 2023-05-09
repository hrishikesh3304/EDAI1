package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PDFselect extends AppCompatActivity {

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfselect);

        listView = findViewById(R.id.listView);

        int[] imageID = {R.drawable.medicalinvoices, R.drawable.healthcheck, R.drawable.medicalrecord};
        String[] name  = {"Medical Invoices","Medical Report", "Other Documents"};

        ArrayList<ListClass> classArrayList = new ArrayList<>();

        for(int i=0 ;  i<3 ;  i++)
        {
            ListClass listelement = new ListClass( name[i],imageID[i]);
            classArrayList.add(listelement);
        }

        CustomListAdapter listAdapter = new CustomListAdapter(PDFselect.this, classArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);





    }
}