package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_display);

        plus = findViewById(R.id.plus);
        listView = findViewById(R.id.listView);
        title = findViewById(R.id.title);
        title.setText(getIntent().getExtras().getString("Family"));

        ArrayList<ListClass> classArrayList = new ArrayList<>();
        List<String> Family_M = new ArrayList<>();

        //SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String selFamily = getIntent().getExtras().getString("Family");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Families");

        Log.d("QWERTY", "----------------------selfam--------------------" + selFamily);

        reference.child(selFamily).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                //Log.d("QWERTY", "onComplete: --------------> " + DocName);
                //Log.d("QWERTY", "onComplete: 1");
                if (task.isSuccessful()) {
                 //   Log.d("QWERTY", "onComplete: 2");
                    DataSnapshot dataSnapshot = task.getResult();
                    Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                    Log.d("QWERTY", "---------->" + dataSnapshot);

                    while (i.hasNext()) {
                        String FamilyMembers = i.next().getKey();
                        Log.d("WERQER", "FAMILY MEMBER -------" + FamilyMembers);

                        ListClass element = new ListClass(FamilyMembers, R.drawable.medicalhistory);
                        Family_M.add(FamilyMembers);
                        classArrayList.add(element);
                        Log.d("QWERTY", "ArrayList---------->" + classArrayList);


                    }



                }

                CustomListAdapter listAdapter = new CustomListAdapter(FamilyDisplay.this, classArrayList);
                listView.setAdapter(listAdapter);
                listView.setClickable(true);


            }
        });
/*
        CustomListAdapter listAdapter = new CustomListAdapter(FamilyDisplay.this, classArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
*/

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FamilyDisplay.this , AddFamilyMem.class);
                intent.putExtra("Family1", getIntent().getExtras().getString("Family") );
                startActivity(intent);



            }
        });




    }
}