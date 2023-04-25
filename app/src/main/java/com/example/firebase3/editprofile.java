package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class editprofile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fstore;

    RadioGroup radio1;
    RadioButton radioButton;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Button button5;

        EditText editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        EditText editTextDate = findViewById(R.id.editTextDate);
        EditText editTextTextPostalAddress = findViewById(R.id.editTextTextPostalAddress);
        EditText editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        EditText editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        EditText editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        EditText editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        EditText editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        EditText editTextTextPersonName66 = findViewById(R.id.editTextTextPersonName66);
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        CheckBox checkBox5 = findViewById(R.id.checkBox5);
        CheckBox checkBox6 = findViewById(R.id.checkBox6);


        radio1 = findViewById(R.id.radio1);

        button5 = findViewById(R.id.button5);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        String stfullname = editTextTextPersonName2.getText().toString();
        String stdob = editTextDate.getText().toString();
        String stbg = editTextTextPersonName6.getText().toString();
        String stadd = editTextTextPostalAddress.getText().toString();
        String stnum = editTextNumberSigned.getText().toString();
        String stfood = editTextTextPersonName3.getText().toString();
        String stdrug = editTextTextPersonName4.getText().toString();
        String stother = editTextTextPersonName5.getText().toString();
        String stsurgical = editTextTextPersonName66.getText().toString();


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stfullname = editTextTextPersonName2.getText().toString();
                String stdob = editTextDate.getText().toString();
                String stbg = editTextTextPersonName6.getText().toString();
                String stadd = editTextTextPostalAddress.getText().toString();
                String stnum = editTextNumberSigned.getText().toString();
                String stfood = editTextTextPersonName3.getText().toString();
                String stdrug = editTextTextPersonName4.getText().toString();
                String stother = editTextTextPersonName5.getText().toString();
                String stsurgical = editTextTextPersonName66.getText().toString();

                String chk1 = checkBox1.getText().toString();
                String chk2 = checkBox2.getText().toString();
                String chk3 = checkBox3.getText().toString();
                String chk4 = checkBox4.getText().toString();
                String chk5 = checkBox5.getText().toString();
                String chk6 = checkBox6.getText().toString();

                int radioID = radio1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);

                String stgender= radioButton.getText().toString();











                if (stfullname.equals("") || stdob.equals("") || stbg.equals("") || stadd.equals("") || stnum.equals("") || stfood.equals("") || stdrug.equals("") || stother.equals("") || stsurgical.equals("")) {
                    Toast.makeText(editprofile.this, "Filling all details is mandatory!", Toast.LENGTH_SHORT).show();
                }
                else{

                    userID = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fstore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("Fullname", stfullname);
                    user.put("DOB", stdob);
                    user.put("Number", stnum);
                    user.put("Address", stadd);
                    user.put("Bloodgroup", stbg);
                    user.put("FoodAllergies", stfood);
                    user.put("DrugAllergies", stdrug);
                    user.put("OtherAllergies", stother);
                    user.put("SurgicalHistory", stsurgical);
                    user.put("Gender", stgender);
                    user.put("row11",chk1);
                    user.put("row12",chk2);
                    user.put("row21",chk3);
                    user.put("row22",chk4);
                    user.put("row31",chk5);
                    user.put("row32",chk6);





                    startActivity(new Intent(editprofile.this, ProfileView.class));


                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(editprofile.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }




            ;


        };
    });




}}