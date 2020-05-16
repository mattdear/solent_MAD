package com.md.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MapFragment extends Fragment {

    MapView mv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, parent);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        mv = getView().findViewById(R.id.map1);
        mv.setMultiTouchControls(true);
        mv.getController().setZoom(10.0);
        mv.getController().setCenter(new GeoPoint(51.05, -0.72));
        moveMap(48.85,2.34);
    }

    public void moveMap(Double lat, Double lon){

        mv = getView().findViewById(R.id.map1);
        mv.setMultiTouchControls(true);
        mv.getController().setZoom(10.0);
        mv.getController().setCenter(new GeoPoint(lat, lon));
        System.out.println("***********************complete***************************");
    }
}
