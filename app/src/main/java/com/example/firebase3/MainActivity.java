package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button button2;
         Button button3;
         TextView textView;

         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button4);
         textView=findViewById(R.id.textView);

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