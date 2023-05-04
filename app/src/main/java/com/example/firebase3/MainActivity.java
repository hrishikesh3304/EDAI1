package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button button2;
         Button button3;
         TextView textView;
         final ConstraintLayout constraintLayout;


         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button4);
         textView=findViewById(R.id.textView);
        constraintLayout = findViewById(R.id.activity_home1_background);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String Type = sh.getString("User_Type", "");
        Log.d("QWERTY", "Type-> " + Type );
        if(Type.equals("Doctor"))
        {
            constraintLayout.setBackgroundResource(R.color.teal_700);
        }

        //HAAD

         button2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this,Login.class));

             }
         });

         button3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, Register.class));

             }
         });

    }
}