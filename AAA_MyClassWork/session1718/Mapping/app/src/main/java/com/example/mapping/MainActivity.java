package com.example.mapping;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;
    double lat;
    double lon;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView) findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(17);
        mv.getController().setCenter(new GeoPoint(50.9349, -1.4219));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            public boolean onItemLongPress(int i, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem fernhurst = new OverlayItem("Fernhurst", "the village of Fernhurst", new GeoPoint(51.05, -0.72));

        // NOTE is just this.getDrawable() if supporting API 21+ only
        fernhurst.setMarker(getDrawable(R.drawable.marker));
        items.addItem(fernhurst);
        items.addItem(new OverlayItem("Blackdown", "highest point in West Sussex", new GeoPoint(51.0581, -0.6897)));
        mv.getOverlays().add(items);

        Button b = (Button) findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
        mv.getController().setZoom(17);
        mv.getController().setCenter(new GeoPoint(lat, lon));
    }
}