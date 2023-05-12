package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;



public class AddPatient extends AppCompatActivity {

    String TextFromQR;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    String docName;



    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_add_patient);

        CodeScannerView scannerView = findViewById(R.id.camView_Mode2);
        CodeScanner mCodeScanner;
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCodeScanner.startPreview();
                        Toast.makeText(AddPatient.this, result.getText(), Toast.LENGTH_SHORT).show();
                        TextFromQR = result.getText();

                        docName = getIntent().getExtras().getString("docName");

                        //TextFromQR = "Hrishikesh popat|test1@gmail.com|123456";

                        fAuth = FirebaseAuth.getInstance();
                        fStore= FirebaseFirestore.getInstance();
                        userId = fAuth.getCurrentUser().getUid();

                        DocumentReference documentReference = fStore.collection("users").document(userId);
                        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("Doctors");

                        QRclass patient = new QRclass(TextFromQR);

                        Log.d("QWERTY", "docName ------------>" + docName);
                        Log.d("QWERTY", "Patient--------->" + patient.getPatient_name() + "  " + patient.getUsername() + "  "  + patient.getPassword() );

                        reference.child(sh.getString("Doctor_name","")).child(patient.getPatient_name()).setValue(patient);

                        Toast.makeText(AddPatient.this, "Patient added successfully!", Toast.LENGTH_SHORT).show();


                    }});
            }});










        //QRScanner cha code,










    }






}