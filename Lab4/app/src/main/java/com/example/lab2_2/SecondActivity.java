package com.example.lab2_2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {


    TextView txtMobile ;
    TextView txtMesaage;
    private static final String PREF_FILE="MyPrefsFile";
    private static final String PRIVATE_FILE="MyPrivateFile";
    public static final  String MOBILE_NUMBER="MOBILE_NUMBER";
    public static final  String MESSAGE      ="MESSAGE";


    Button btn_Back;
    Button btn_W_SH;
    Button btn_R_SH;

    Button btn_W_IS;
    Button btn_R_IS;

    Button btn_W_DB;
    Button btn_R_DB;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        txtMobile=findViewById(R.id.txtMobile);
        txtMesaage=findViewById(R.id.txtMessage);
        btn_Back=findViewById(R.id.back_btn);

        btn_W_SH=findViewById(R.id.W_SHbtn);
        btn_R_SH=findViewById(R.id.R_SHbtn);

        btn_W_IS=findViewById(R.id.W_ISbtn);
        btn_R_IS=findViewById(R.id.R_ISbtn);

        btn_W_DB=findViewById(R.id.W_DBbtn);
        btn_R_DB=findViewById(R.id.R_DBbtn);

        Intent incomingIntent = getIntent();
        String mobile =incomingIntent.getStringExtra(SecondActivity.MOBILE_NUMBER);
        String msg    =incomingIntent.getStringExtra(SecondActivity.MESSAGE);

        txtMobile.setText(mobile);
        txtMesaage.setText(msg);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//  SHARED PREFERENCE
        btn_W_SH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences Preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=Preferences.edit();
                editor.putString(SecondActivity.MOBILE_NUMBER,txtMobile.getText().toString());
                editor.putString(SecondActivity.MESSAGE,txtMesaage.getText().toString());
                editor.commit();
                txtMobile.setText("written in shared file");
                txtMesaage.setText("written in shared file");
            }
        });
        btn_R_SH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences Preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);
                txtMobile.setText(Preferences.getString(SecondActivity.MOBILE_NUMBER,getString(R.string.n_a)));
                txtMesaage.setText(Preferences.getString(SecondActivity.MESSAGE,getString(R.string.n_a)));


            }
        });

//  INTERNAL STORAGE
        btn_W_IS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos=openFileOutput(SecondActivity.PRIVATE_FILE,Context.MODE_PRIVATE);
                    DataOutputStream  dos=new DataOutputStream(fos);
                    dos.writeUTF(txtMobile.getText().toString());
                    dos.writeUTF(txtMesaage.getText().toString());
                    dos.close();
                    fos.close();
                    txtMobile.setText("written in private file");
                    txtMesaage.setText("written in private file");

                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this,"don't locate the private file",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this,"don't create the private file",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_R_IS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(SecondActivity.PRIVATE_FILE);
                    DataInputStream dis=new DataInputStream(fis);
                    txtMobile.setText(dis.readUTF());
                    txtMesaage.setText(dis.readUTF());
                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this,"don't locate the private file",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this,"don't create the private file",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_W_DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = txtMobile.getText().toString();
                String message = txtMesaage.getText().toString();

                // Save to DB
                DatabaseAdaptor adapter = new DatabaseAdaptor(SecondActivity.this);
                long id=adapter.addEntry(new DataClass(mobile, message));


                txtMesaage.setText("written in database");
            }
        });


        btn_R_DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAdaptor adapter = new DatabaseAdaptor(SecondActivity.this);
                DataClass data = adapter.getEntry(txtMobile.getText().toString());
                    txtMobile.setText(data.getMobileNumber());
                    txtMesaage.setText(data.getMessage());


            }
        });

    }



}