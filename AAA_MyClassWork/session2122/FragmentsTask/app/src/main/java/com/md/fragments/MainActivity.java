package com.md.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MapFragment mapFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listfragment);
    }

    public void mapPush(Double lat, Double lon) {
        mapFragment.moveMap(lat, lon);
    }
}

