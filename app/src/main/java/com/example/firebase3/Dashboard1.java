package com.example.firebase3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Dashboard1 extends AppCompatActivity {
TextView titleHome;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userId;
String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);
        titleHome = findViewById(R.id.titleHome);
        CardView cardMyFamily;
        CardView cardMyMedicine;
        CardView cardProfile;
        CardView cardMyDoctors;
        ImageView qr;


        cardMyFamily = findViewById(R.id.cardDocumnet);
        cardMyMedicine = findViewById(R.id.cardMyMedicine);
        cardProfile = findViewById(R.id.cardProfile);
        cardMyDoctors = findViewById(R.id.cardBill);
        qr = findViewById(R.id.qr);


        fAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                titleHome.setText("Welcome " + documentSnapshot.getString("Fullname"));
                temp= documentSnapshot.getString("Fullname");

            }
        });
//

        cardProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Dashboard1.this, ProfileView.class));
            }
        });

        cardMyMedicine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Dashboard1.this, UploadMain.class));
            }
        });

        cardMyDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard1.this, PatientQr.class);
                i.putExtra("Title", temp);
                startActivity(i);
            }
        });



     /*   cardDocumnet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Dashboard1.this, UploadMain.class));
            }
        });

        cardBill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Dashboard1.this, UploadMain.class));
            }
        });
*/



    }
}