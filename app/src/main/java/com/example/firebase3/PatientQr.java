package com.example.firebase3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PatientQr extends AppCompatActivity {

    TextView PatientName;
    ImageView PatientQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_qr);

        PatientName = findViewById(R.id.etText);
        PatientQR=  findViewById(R.id.imageCode);

        PatientName.setText(getIntent().getExtras().getString("Title"));

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String QRtext = "|" + sh.getString("UserName", "") + "|" + sh.getString("Password", "") + "|" ;


        MultiFormatWriter mWriter = new MultiFormatWriter();
        try {

            BitMatrix mMatrix = mWriter.encode(QRtext, BarcodeFormat.QR_CODE, 600,600);
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);
            PatientQR.setImageBitmap(mBitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }







    }
}