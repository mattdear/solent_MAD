package com.md.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListFragment extends androidx.fragment.app.ListFragment {
    String[] entries = {"London", "Paris", "New York"},
            latitude = {"51.51", "48.85", "40.75"},
            longitude = {"-0.1", " 2.34", "-74"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, entries);
        setListAdapter(adapter);
    }


    public void onListItemClick(ListView lv, View v, int index, long id) {
        Activity activity = getActivity();
        Double lat = Double.valueOf(latitude[index]);
        Double lon = Double.valueOf(longitude[index]);
        this.getActivity().mapPush(lat, lon);

        Toast.makeText(getActivity(), latitude[index], Toast.LENGTH_LONG).show();
    }
}
