package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFamily extends AppCompatActivity {

    EditText fname;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);

        fname = findViewById(R.id.name);
        addBtn  = findViewById(R.id.add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addFamily = fname.getText().toString();
                SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Families");

                QRclass familymem = new QRclass(sh.getString("Username",""),sh.getString("Password",""),sh.getString("Patient_name",""));

                reference.child(addFamily).child(sh.getString("Patient_name","")).setValue(familymem);

                Intent i = new Intent(AddFamily.this , MyFamily.class);
                startActivity(i);



            }
        });


    }
}