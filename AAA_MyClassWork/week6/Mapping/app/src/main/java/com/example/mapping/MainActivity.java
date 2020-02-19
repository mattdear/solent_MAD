package com.example.mapping;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    MapView mv;
    double lat;
    double lon;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, prefs);

        setContentView(R.layout.activity_main);

        mv = (MapView) findViewById(R.id.map1);


        double lat = Double.parseDouble(prefs.getString("lat", "50.9"));
        double lon = Double.parseDouble(prefs.getString("lon", "-1.4"));
        double zoo = Double.parseDouble(prefs.getString("zoo", "17"));
        String map = prefs.getString("map", "NONE");

        if (map.equals("HBM")) {
            mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
        } else {
            mv.setTileSource(TileSourceFactory.MAPNIK);
        }

        mv.setMultiTouchControls(true);
        mv.getController().setZoom(zoo);
        mv.getController().setCenter(new GeoPoint(lat, lon));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.choosemap) {
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);
            return true;
        } else if (item.getItemId() == R.id.setlocation) {
            Intent intent = new Intent(this, SetLocation.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (item.getItemId() == R.id.mappreferences) {
            Intent intent = new Intent(this, MapPreferences.class);
            startActivityForResult(intent, 2);
        } else if (item.getItemId() == R.id.selectmap) {
            Intent intent = new Intent(this, SelectMap.class);
            startActivityForResult(intent, 0);
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if (hikebikemap == true) {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                } else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }

        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                lat = extras.getDouble("lat", lat);
                lon = extras.getDouble("lon", lon);
                mv.getController().setZoom(17);
                mv.getController().setCenter(new GeoPoint(lat, lon));

            }
        }
    }
}