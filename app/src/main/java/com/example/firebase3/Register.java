package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView editTextTextPassword3;
        Button button4;
        TextView editTextTextPassword;
        TextView editTextTextPersonName;



        TextView editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword3=findViewById(R.id.editTextTextPassword3);
        button4=findViewById(R.id.button4);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        auth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        editTextTextPassword=findViewById(R.id.editTextTextPassword);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=editTextTextEmailAddress.getText().toString();
                String password1=editTextTextPassword3.getText().toString();
                String fullname=editTextTextPersonName.getText().toString();
                System.out.println("FULLNAME is"+fullname);

                /*System.out.print("email="+email1);
                System.out.print("pwd="+password1); */

                if(editTextTextEmailAddress.getText().toString().equals("") || editTextTextPassword3.getText().toString().equals(""))
                {
                    Toast.makeText(Register.this, "Enter email and password!", Toast.LENGTH_SHORT).show();
                }
                else if ( editTextTextPassword.getText().toString().equals(editTextTextPassword3.getText().toString()) )
                {
                    registration(email1,password1,fullname);

                }
                else
                {
                    Toast.makeText(Register.this, "Re-enter password", Toast.LENGTH_SHORT).show();
                }

            }
        });






        }


    private void registration(String email1, String password1, String fullname)
    {
        auth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    userID = auth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Fullname",fullname);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            System.out.println("Firestore success!");
                        }
                    });

                }
                else{
                    Toast.makeText(Register.this, "Failed!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}