package com.example.simpletexteditor;

import android.content.Intent;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    String textOutput = "";
    String textInput = "";
    String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fileName = prefs.getString("fileName", "textedit");
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
        } else if (item.getItemId() == R.id.file) {
            Intent intent = new Intent(this, FilePreference.class);
            startActivityForResult(intent, 2);
        }
        return false;
    }

    public void saveTxtFile() {
        try {
            PrintWriter pw =
                    new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName + ".txt"));

            EditText editText = (EditText) findViewById(R.id.etTextInputOutput);
            textInput = editText.getText().toString();

            pw.println(textInput);
            pw.close();

        } catch (IOException e) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).
                    setMessage("ERROR: " + e).show();
        }
    }

    public void loadTxtFile() {
        try {
            textOutput = "";
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName + ".txt");
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

