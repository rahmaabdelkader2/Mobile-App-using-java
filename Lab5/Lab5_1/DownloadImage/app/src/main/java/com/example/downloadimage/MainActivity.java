package com.example.downloadimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    TextView edtURL;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnDownload=findViewById(R.id.downloadbtn);
        edtURL=findViewById(R.id.editURL);
        imgView=findViewById(R.id.imgView);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask task = new DownloadTask();
                task.execute(edtURL.getText().toString());
            }
        });



        }

    private Bitmap Download(String url) throws IOException {
        // bdayto protocol we a5ro extension
        // ay no3 connection lazm a3ml obj mn url
        URL imgURL = new URL("https://miro.medium.com/v2/resize:fit:6018/1*3rewUBdM1VKZrBGd7UDoFA.png");

        HttpsURLConnection connection  = (HttpsURLConnection) imgURL.openConnection();

        // http has response and request
        connection.setRequestMethod("GET");

        connection.connect();

        int RespondCode = connection.getResponseCode();

        InputStream is = connection.getInputStream();

        return BitmapFactory.decodeStream(is);
    }

    class DownloadTask extends AsyncTask<String,Integer,Bitmap>
    {


        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap result = null ;

            try {
                result = Download(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imgView.setImageBitmap(result);

        }
    }

}