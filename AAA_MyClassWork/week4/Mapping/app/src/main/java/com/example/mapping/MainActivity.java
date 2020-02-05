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

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;
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

        Button b = (Button) findViewById(R.id.btn1);
        b.setOnClickListener(this);
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
        }
        return false;
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
    }
}