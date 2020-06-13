package com.app.webcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadContent downloadContent=new DownloadContent();
        String result=null;
        try {
            result=downloadContent.execute("https://www.zappycode.com/").get();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("result",result);
    }

    public class DownloadContent extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
//            Log.i("url", strings[0]);

            //to convert string into url
            String result="";
            URL url;
            HttpURLConnection httpURLConnection=null;
            try {
                url =new URL(urls[0]);
                //to create url connection
                httpURLConnection= (HttpURLConnection) url.openConnection();
                //to gether the data on hitting the url
                InputStream inputStream= httpURLConnection.getInputStream();
                //to read the data
                InputStreamReader reader=new InputStreamReader(inputStream);
                //to read data char by char
                int data =reader.read();
                //run the loop to read the data until it reach end
                while(data != -1){
                    //read data letter by letter
                   char current =(char)data;
                   result +=current;
                    data=reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to return";
            }

        }
    }
}
