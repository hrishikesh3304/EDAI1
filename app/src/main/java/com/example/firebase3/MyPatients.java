package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPatients extends AppCompatActivity {

    ListView listView;
    ImageView plus;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patients);

        listView = findViewById(R.id.listView);
        plus = findViewById(R.id.plus);

        List<String> Patient_name = new ArrayList<>();

        ArrayList<ListClass> classArrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Doctors");

        fAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);


        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                //String DocName =  documentSnapshot.getString("Fullname");
                String DocName = "Hrishikesh Potnis";


                String finalDocName = DocName;
                reference.child(DocName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("QWERTY", "onComplete: --------------> " + DocName);
                Log.d("QWERTY", "onComplete: 1");
                if (task.isSuccessful()) {
                    Log.d("QWERTY", "onComplete: 2");
                    DataSnapshot dataSnapshot = task.getResult();
                    Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                    Log.d("QWERTY", "---------->" + dataSnapshot);
                    while (i.hasNext()) {
                        String PName = i.next().getKey();
                        Log.d("WERQER", "PATIENT NAME -------" + PName);
                        ListClass element = new ListClass(PName, R.drawable.patients);
                        Patient_name.add(PName);
                        classArrayList.add(element);
                    }
                }}});}});

                CustomListAdapter listAdapter = new CustomListAdapter(MyPatients.this, classArrayList);

                //Log.d("QWERTY", String.valueOf(classArrayList));

                listView.setAdapter(listAdapter);
                listView.setClickable(true);


                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                        }
            });
                }}
