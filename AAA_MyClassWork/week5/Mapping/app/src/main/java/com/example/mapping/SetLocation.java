package com.example.mapping;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetLocation extends AppCompatActivity implements View.OnClickListener {

    double lat;
    double lon;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        EditText et1 = (EditText) findViewById(R.id.et1);
        EditText et2 = (EditText) findViewById(R.id.et2);
        if (et1.getText().toString().isEmpty()) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("Latitude should not be empty").show();
            return;
        } else {
            lat = Double.parseDouble(et1.getText().toString());
        }
        if (et2.getText().toString().isEmpty()) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("Longitude should not be empty").show();
            return;
        } else {
            lon = Double.parseDouble(et2.getText().toString());
        }

        bundle.putDouble("lat", lat);
        bundle.putDouble("lon", lon);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}

