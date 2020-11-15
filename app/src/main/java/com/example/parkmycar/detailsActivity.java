package com.example.parkmycar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;

public class detailsActivity extends Fragment {
    Intent intent;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHelper db=new DatabaseHelper(getContext());
        View fv1= inflater.inflate(R.layout.details, container, false);
        ArrayList<HashMap<String, String>> userList = db.GetLocation();
        ListView lv = (ListView) fv1.findViewById(R.id.location_list);
        ListAdapter adapter = new SimpleAdapter(getContext(), userList, R.layout.list_row,new String[]{"name","description","capacity"}, new int[]{R.id.name, R.id.desc, R.id.capacity});
        lv.setAdapter(adapter);
        return fv1;
    }
}
