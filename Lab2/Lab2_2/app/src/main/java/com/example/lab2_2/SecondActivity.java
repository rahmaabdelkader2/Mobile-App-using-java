package com.example.lab2_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {


    TextView txtMobile ;
    TextView txtMesaage;

    Button btn_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        txtMobile=findViewById(R.id.txtMobile);
        txtMesaage=findViewById(R.id.txtMessage);
        btn_Back=findViewById(R.id.back_btn);
        Intent incomingIntent = getIntent();
        String mobile =incomingIntent.getStringExtra("MOBILE_NUMBER");
        String msg    =incomingIntent.getStringExtra("MESSAGE");

        txtMobile.setText(mobile);
        txtMesaage.setText(msg);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}