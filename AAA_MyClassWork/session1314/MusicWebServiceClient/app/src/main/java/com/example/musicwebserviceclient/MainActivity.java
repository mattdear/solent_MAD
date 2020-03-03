package com.example.musicwebserviceclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    class MyTask extends AsyncTask<String, Void, String> {
        public String doInBackground(String... userInput) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://www.free-map.org.uk/course/ws/hits.php?artist=" + userInput[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    return result;
                } else {
                    return "HTTP ERROR: " + conn.getResponseCode();
                }
            } catch (IOException e) {
                return e.toString();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

        public void onPostExecute(String result) {
            TextView tv1 = (TextView) findViewById(R.id.tv1);
            tv1.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.searchbtn);
        search.setOnClickListener(this);
    }

    public void onClick(View v) {
        EditText et = (EditText) findViewById(R.id.et1);
        String userInput = et.getText().toString();
        MyTask t = new MyTask();
        t.execute(userInput);
    }
}
