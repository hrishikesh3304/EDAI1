package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        int[] imageID;
        String[] name;

        ArrayList<ListClass> classArrayList = new ArrayList<>();


        CustomListAdapter listAdapter =  new CustomListAdapter(DoctorList.this, classArrayList);


    }
}