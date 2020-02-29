package com.example.simpletexteditor;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class FilePreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}


