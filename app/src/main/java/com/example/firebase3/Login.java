package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView editTextTextEmailAddress2;
    TextView editTextTextPassword2;
    Button button;
    FirebaseAuth auth;
    ConstraintLayout constraintLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress2=findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2=findViewById(R.id.editTextTextPassword2);
        button=findViewById(R.id.button);
        auth= FirebaseAuth.getInstance();
        constraintLayout=findViewById(R.id.Login_Layout);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        if(sh.getString("User_Type", "").equals("Doctor"))
        {
            constraintLayout.setBackgroundResource(R.color.teal_700);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2=editTextTextEmailAddress2.getText().toString();
                String password2=editTextTextPassword2.getText().toString();

                /*System.out.print("email="+email1);
                System.out.print("pwd="+password1); */

                if(editTextTextEmailAddress2.getText().toString().equals("") || editTextTextPassword2.getText().toString().equals(""))
                {
                    Toast.makeText(Login.this, "Enter email and password!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login(email2,password2);
                }
            }
        });
    }
    private void login(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if(sh.getString("User_Type", "").equals("Doctor"))
                    {
                        Intent intent = new Intent(Login.this,DoctorDashboard.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(Login.this,Dashboard1.class);
                        startActivity(intent);
                    }

                }
                else{
                    Toast.makeText(Login.this, "Enter correct email and password ", Toast.LENGTH_SHORT).show();
                }


            }
/*
            @Override
            public void onSuccess(AuthResult authResult) {

                startActivity(new Intent(Login.this,Dashboard1.class));

            }
            @Override
            public void onFailure(AuthResult authResult){

            } */
        });
    }
}