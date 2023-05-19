package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyDisplay extends AppCompatActivity {

    ListView listView;
    ImageView plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_display);

        plus = findViewById(R.id.plus);

        ArrayList<ListClass> classArrayList = new ArrayList<>();
        List<String> Family_M = new ArrayList<>();

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String selFamily = sh.getString("Family","");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Families");

        reference.child(selFamily).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                //Log.d("QWERTY", "onComplete: --------------> " + DocName);
                Log.d("QWERTY", "onComplete: 1");
                if (task.isSuccessful()) {
                    Log.d("QWERTY", "onComplete: 2");
                    DataSnapshot dataSnapshot = task.getResult();
                    Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                    Log.d("QWERTY", "---------->" + dataSnapshot);
                    while (i.hasNext()) {
                        String FamilyMembers = i.next().getKey();
                        Log.d("WERQER", "PATIENT NAME -------" + FamilyMembers);
                        ListClass element = new ListClass(FamilyMembers, R.drawable.medicalhistory);
                        Family_M.add(FamilyMembers);
                        classArrayList.add(element);
                        Log.d("QWERTY", "ArrayList---------->" + classArrayList);
                        Log.d("oiwejjowi", "element --------------------" + element);

                    }


                }

                CustomListAdapter listAdapter = new CustomListAdapter(FamilyDisplay.this, classArrayList);

                //Log.d("QWERTY", String.valueOf(classArrayList));

                listView.setAdapter(listAdapter);
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}