package com.example.mapping;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectMap extends ListActivity {
    String[] data, details;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new String[]{"Regular", "Hike and Bike"};
        details = new String[] { "This is the regular map for normal people.", "This is the hike bike map for hike bikers." };

        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);

    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(SelectMap.this, android.R.layout.simple_list_item_1, data);
        }

        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.select_map_entry, parent, false);
            TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                    (TextView)view.findViewById(R.id.poi_desc);
            title.setText(data[index]);
            detail.setText(details[index]);
            return view;
        }
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        boolean hikebikemap = false;
        if (index == 1) {
            hikebikemap = true;
        }
        bundle.putBoolean("com.example.hikebikemap", hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
