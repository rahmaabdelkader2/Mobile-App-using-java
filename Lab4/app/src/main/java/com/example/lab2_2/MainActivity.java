package com.example.lab2_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btnNext;
    private Button btnExit;

    private EditText editPhone;

    private EditText editMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit =findViewById(R.id.btnExit);
        btnNext=findViewById(R.id.btnNext);
        editMsg=findViewById(R.id.editMsg);
        editPhone=findViewById(R.id.editPhone);

        //event handling
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                // b5li el data tro7 m3 el activity 34an tit3rd f el page eli b3dha
                intent.putExtra("MOBILE_NUMBER",editPhone.getText().toString());
                intent.putExtra("MESSAGE",editMsg.getText().toString());
                startActivity(intent);
            }
        });

        Log.i(TAG, "onCreate: ");
        }

        public void onRestart(){
        super.onRestart();
            Log.i(TAG, "onRestart: ");
        }
        
        public void onStart(){
        super.onStart();
            Log.i(TAG, "onStart: ");
        }
        
        public void onResume(){
        super.onResume();
            Log.i(TAG, "onResume: ");
        }
        
        public void onPause(){
        super.onPause();
            Log.i(TAG, "onPause: ");
        }
        
        public void onStop(){
        super.onStop();
            Log.i(TAG, "onStop: ");
        }
        public void exit(View view){
        finish();
        }

    }
