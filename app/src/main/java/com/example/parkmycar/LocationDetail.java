package com.example.parkmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class LocationDetail extends AppCompatActivity {
    TextView name1,desc1,capacity1,openingtime1,addr1,cost1,contacts1,uniquecode1,type1;
    String name2,desc2,capacity2,openingtime2,addr2,cost2,contacts2,uniquecode2,type2;
    Button btn,btn1;
    private ImageView qrimg;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmapResult;
    private static final String TAG = "Location Details Activity";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        btn1=findViewById(R.id.bookparking);
        btn=findViewById(R.id.back);
        qrimg=findViewById(R.id.qr_image);
        name1=(TextView)findViewById(R.id.name);
        desc1=(TextView)findViewById(R.id.desc);
        capacity1=(TextView)findViewById(R.id.capacity);
        openingtime1=(TextView)findViewById(R.id.opening_time);
        addr1=(TextView)findViewById(R.id.addr);
        cost1=(TextView)findViewById(R.id.cost);
        type1=(TextView)findViewById(R.id.type);
        contacts1=(TextView)findViewById(R.id.contacts);
        uniquecode1=(TextView)findViewById(R.id.uniquecode);
        Intent intent = getIntent();
        if(intent != null){
            name2 = intent.getStringExtra("name");
            cost2=intent.getStringExtra("cost");
            capacity2=intent.getStringExtra("capacity");
            desc2=intent.getStringExtra("description");
            openingtime2=intent.getStringExtra("opening_time");
            addr2=intent.getStringExtra("address");
            type2=intent.getStringExtra("parking_type");
            contacts2=intent.getStringExtra("contacts");
            uniquecode2=intent.getStringExtra("parking_code");
        }

        com.google.zxing.Writer writer = new QRCodeWriter();
        // String finaldata = Uri.encode(data, "utf-8");
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(uniquecode2, null, QRGContents.Type.TEXT, smallerDimension);
        try {
            bitmapResult = qrgEncoder.encodeAsBitmap();
            qrimg.setImageBitmap(bitmapResult);

        } catch (WriterException e) {
            Log.v(TAG, e.toString());
        }


        name1.setText(name2);
        desc1.setText(desc2);
        capacity1.setText(capacity2);
        openingtime1.setText(openingtime2);
        cost1.setText(cost2);
        contacts1.setText(contacts2);
        type1.setText(type2);
        uniquecode1.setText(uniquecode2);
        addr1.setText(addr2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetail.this, HomeScreen.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetail.this, bookparking.class);
                intent.putExtra("cost",cost2);
                startActivity(intent);
            }
        });

    }
}