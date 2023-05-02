package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class userType extends AppCompatActivity {

    CardView Doctor_Card, Patient_Card;
 //Faltu committ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        Doctor_Card = findViewById(R.id.cardViewStudent);
        Patient_Card = findViewById(R.id.cardViewTeacher);

        Doctor_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("User_Type", "Doctor");
                myEdit.commit();
                Intent intent = new Intent(userType.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Patient_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("User_Type", "Patient");
                myEdit.commit();
                Intent intent = new Intent(userType.this, MainActivity.class);
                startActivity(intent);

            }
        });





    }


}