package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDoctors extends AppCompatActivity {

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctors);

        listView = findViewById(R.id.listView);

        List<String> Doc_name = new ArrayList<>();
        //List<String> Doc_display_name = new ArrayList<>();
        ArrayList<ListClass> classArrayList = new ArrayList<>();

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Task<DataSnapshot> reference = database.getReference("Doctors").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    Log.d("QWERTY", "onComplete: 2");

                    DataSnapshot dataSnapshot = task.getResult();

                    Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                    Log.d("QWERTY", "---------->" + dataSnapshot);
                    while (i.hasNext()) {
                        String Doctor = i.next().getKey();

                        Log.d("QWERTY", "Doctor----------->"+Doctor);

                        Iterator<DataSnapshot> Patient_Iterator = dataSnapshot.child(Doctor).getChildren().iterator();
                        while (Patient_Iterator.hasNext()){

                            String Pname = Patient_Iterator.next().getKey();
                            Log.d("QWERTY", "Patient--------------->");

                            if(Pname.equals(sh.getString("Patient_name",""))) {

                                ListClass element = new ListClass("Dr. " + Doctor, R.drawable.doctor);
                                Doc_name.add(Doctor);
                                classArrayList.add(element);
                            }

                        }

                    }
                }
                CustomListAdapter listAdapter = new CustomListAdapter(MyDoctors.this, classArrayList);



                listView.setAdapter(listAdapter);


            }
        });

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyDoctors.this);
                alertDialog.setTitle("AlertDialog");
                String[] items = {"View Only","View/Write"};
                int checkedItem = 1;
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:



                                break;
                            case 1:

                                break;

                        }
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyDoctors.this);
                builder.setMessage("Do you want to remove Dr. "+ Doc_name.get(i)+" ?" );
                builder.setTitle("Alert !");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {



                    SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Doctors");

                    reference.child(Doc_name.get(i)).child(sh.getString("Patient_name","")).removeValue();
                    classArrayList.remove(i);
                    Doc_name.remove(i);

                    Toast.makeText(MyDoctors.this, "Doctor removed successfully", Toast.LENGTH_SHORT).show();

                });


                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;

            }
        });
















    }





}