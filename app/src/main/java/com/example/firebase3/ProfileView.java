package com.example.firebase3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class ProfileView extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        TextView Nameview = findViewById(R.id.Nameview);
        TextView DOBbiew = findViewById(R.id.DOBbiew);
        TextView Emergencyview = findViewById(R.id.Emergencyview);
        TextView Addressview = findViewById(R.id.Addressview);
        TextView Bloodview = findViewById(R.id.Bloodview);
        TextView Foodview = findViewById(R.id.Foodview);
        TextView Drugview = findViewById(R.id.Drugview);
        TextView Otherview = findViewById(R.id.Otherview);
        TextView Surgeryview = findViewById(R.id.Surgeryview);
        Button button3 = findViewById(R.id.button3);
        TextView Genderview = findViewById(R.id.Genderview);
        TextView table11 = findViewById(R.id.table11);
        TextView table12 =findViewById(R.id.table12);
        TextView table21 = findViewById(R.id.table21);
        TextView table22 = findViewById(R.id.table22);
        TextView table31 = findViewById(R.id.table31);
        TextView table32 = findViewById(R.id.table32);



        fAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                Nameview.setText("Name :  " + documentSnapshot.getString("Fullname"));
                DOBbiew.setText("Date of Birth : " + documentSnapshot.getString("DOB"));
                Emergencyview.setText("Emergency Contact : " + documentSnapshot.getString("Number"));
                Addressview.setText("Postal Address : " + documentSnapshot.getString("Address"));
                Bloodview.setText("Blood Group : " + documentSnapshot.getString("Bloodgroup"));
                Foodview.setText("Food Allergies : " + documentSnapshot.getString("FoodAllergies"));
                Drugview.setText("Drug Allergies : " + documentSnapshot.getString("DrugAllergies"));
                Otherview.setText("Other Allergies : "+ documentSnapshot.getString("OtherAllergies"));
                Surgeryview.setText("Surgical History : "+ documentSnapshot.getString("SurgicalHistory"));
                Genderview.setText("Gender : "+ documentSnapshot.getString("Gender"));
                table11.setText(""+ documentSnapshot.getString("row11"));
                table12.setText(""+ documentSnapshot.getString("row12"));
                table21.setText(""+ documentSnapshot.getString("row21"));
                table22.setText(""+ documentSnapshot.getString("row22"));
                table31.setText(""+ documentSnapshot.getString("row31"));
                table32.setText(""+ documentSnapshot.getString("row32"));






            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileView.this, editprofile.class));

            }
        });

    }
}