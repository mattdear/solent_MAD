package com.example.mapping;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class MapPreferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
