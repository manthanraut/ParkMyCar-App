package com.example.parkmycar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
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
        ListAdapter adapter = new SimpleAdapter(getContext(), userList, R.layout.list_row,new String[]{"name","cost","capacity"}, new int[]{R.id.name, R.id.cost, R.id.capacity});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor res = db.getLocationList();
                //res.moveToFirst();
                res.moveToPosition(position);
                Intent intent = new Intent(getActivity(), LocationDetail.class);
                intent.putExtra("name", res.getString(1));
                intent.putExtra("cost",res.getString(9));
                intent.putExtra("capacity", res.getString(3));
                intent.putExtra("description", res.getString(2));
                intent.putExtra("opening_time",  res.getString(7));
                intent.putExtra("address",  res.getString(8));
                intent.putExtra("parking_type", res.getString(10));
                intent.putExtra("contacts", res.getString(11));
                intent.putExtra("parking_code",  res.getString(12));
                startActivity(intent);
            }
        });
        return fv1;
    }
}

