package com.example.parkmycar;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View.OnClickListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;




import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class myhistory1 extends Fragment {
    private static final int CAPTURE_PICCODE = 989;
    private static final int GET_IMAGE = 990;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude,val;
    Button button,btn,submitslot;
    TextView text;

    Uri imageUri;
    ImageView imageView;
    DatabaseHelper databaseHelper;
    EditText name,desc,capacity,openingtime,addr,cost,contacts,uniquecode,type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_myhistory,
                container, false);
        this.desc=(EditText) rootView.findViewById(R.id.desc);
        this.name=(EditText)rootView.findViewById(R.id.name);
        this.capacity=(EditText)rootView.findViewById(R.id.capacity);
        this.openingtime=(EditText)rootView.findViewById(R.id.opening_time);
        this.addr=(EditText)rootView.findViewById(R.id.addr);
        this.cost=(EditText)rootView.findViewById(R.id.cost);
        this.contacts=(EditText)rootView.findViewById(R.id.contacts);
        this.uniquecode=(EditText)rootView.findViewById(R.id.parkingcode);
        this.type=(EditText)rootView.findViewById(R.id.type);
        val = "P"+((int)(Math.random()*9000)+1000);
        this.uniquecode.setText(val);
        submitslot=(Button)rootView.findViewById(R.id.submit);
        ActivityCompat.requestPermissions(getActivity(),new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        button = (Button) rootView.findViewById(R.id.camera_button);
        imageView = (ImageView) rootView.findViewById(R.id.image);
        btn=(Button)rootView.findViewById(R.id.camera_button2);
        this.databaseHelper = new DatabaseHelper(getContext());
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_PICCODE);

            }
        });
        submitslot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                locationManager=(LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then
                    getLocation();
                }

                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data=baos.toByteArray();
                //code for storing location inside database
                String aa = myhistory1.this.desc.getText().toString();
                String bb = myhistory1.this.name.getText().toString();
                String cc = myhistory1.this.capacity.getText().toString();
                String dd = myhistory1.this.latitude;
                String ee=myhistory1.this.longitude;
                String ff = myhistory1.this.openingtime.getText().toString();
                String gg = myhistory1.this.addr.getText().toString();
                String hh = myhistory1.this.cost.getText().toString();
                String ii = myhistory1.this.contacts.getText().toString();
                String jj = myhistory1.this.uniquecode.getText().toString();
                String kk = myhistory1.this.type.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",bb);
                contentValues.put("description", aa);
                contentValues.put("capacity",cc);
                contentValues.put("lat",dd);
                contentValues.put("long",ee);
                contentValues.put("image",data);
                contentValues.put("opening_time",ff);
                contentValues.put("address", gg);
                contentValues.put("cost",hh);
                contentValues.put("contacts",ii);
                contentValues.put("parking_code",jj);
                contentValues.put("parking_type",kk);
                myhistory1.this.databaseHelper.insertLocation(contentValues);
                /*
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("location");
                locationhelper locationclass= new locationhelper(bb,aa,cc,dd,ee,data);
                reference.child(bb).setValue(locationclass);*/
                Toast.makeText(getActivity(),"New Location added successfully",Toast.LENGTH_SHORT).show();
                Intent in = new Intent();
                in.setClass(getActivity(), HomeScreen.class);
                startActivity(in);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        return rootView;
    }
    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                //text.setText(latitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                //text.setText(latitude);
            }
            else
            {
                Toast.makeText(getContext(), "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(getContext());

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GET_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_PICCODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();



                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                imageView.setImageBitmap(bitmap);

            }
        }
        else if(requestCode == GET_IMAGE){
            if (resultCode == Activity.RESULT_OK) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);

            }
        }
    }


}