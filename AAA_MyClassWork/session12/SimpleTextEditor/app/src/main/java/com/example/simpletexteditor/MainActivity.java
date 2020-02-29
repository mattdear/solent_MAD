package com.example.simpletexteditor;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    String textOutput;
    String textInput;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fileName = prefs.getString(fileName,"textedit");
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            saveTxtFile();
            return true;
        } else if (item.getItemId() == R.id.load) {
            loadTxtFile();
            return true;
        }
        return false;
    }

    public void saveTxtFile() {
        try {
            fileName = fileName + ".txt";

            PrintWriter pw =
                    new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + fileName));

            EditText editText = (EditText) findViewById(R.id.etTextInputOutput);
            textInput = editText.getText().toString();


            pw.println(textInput);
            pw.close(); // close the file to ensure data is flushed to file

        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }

    public void loadTxtFile() {
        try {
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                textOutput = textOutput + line;
            }
            reader.close();

            EditText editText = (EditText) findViewById(R.id.etTextInputOutput);
            editText.setText(textOutput);


        } catch (IOException e) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).
                    setMessage("ERROR: " + e).show();

        }

    }

}

