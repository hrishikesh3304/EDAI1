package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyFamily extends AppCompatActivity {

    ListView listView;
    ImageView plus;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_family);

        listView = findViewById(R.id.listView);
        plus = findViewById(R.id.plus);



        ArrayList<ListClass> classArrayList = new ArrayList<>();
        List<String> Family_name = new ArrayList<>();

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Task<DataSnapshot> reference = database.getReference("Families").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    Log.d("QWERTY", "onComplete: 2");

                    DataSnapshot dataSnapshot = task.getResult();

                    Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                    Log.d("QWERTY", "---------->" + dataSnapshot);
                    while (i.hasNext()) {
                        String Family = i.next().getKey();

                        Log.d("QWERTY", "Family----------->"+ Family);

                        Iterator<DataSnapshot> Patient_Iterator = dataSnapshot.child(Family).getChildren().iterator();
                        while (Patient_Iterator.hasNext()){

                            String Pname = Patient_Iterator.next().getKey();
                            Log.d("QWERTY", "Patient--------------->");

                            if(Pname.equals(sh.getString("Patient_name",""))) {

                                ListClass element = new ListClass(Family, R.drawable.bigfamily);
                                Family_name.add(Family);
                                classArrayList.add(element);
                            }

                        }

                    }
                }

        CustomListAdapter listAdapter = new CustomListAdapter(MyFamily.this, classArrayList);


        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                Intent intent = new Intent(MyFamily.this, FamilyDisplay.class);
                intent.putExtra("Family", Family_name.get(i));
                startActivity(intent);


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {




                return true;
            }
        });


    }
});
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(MyFamily.this, AddFamily.class);
                startActivity(i);

            }
        });





    }}