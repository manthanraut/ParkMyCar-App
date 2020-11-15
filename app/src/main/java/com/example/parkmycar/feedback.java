package com.example.parkmycar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import javax.microedition.khronos.egl.EGLDisplay;

public class feedback extends Fragment {
    EditText name, email, message;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View fragment1View = inflater.inflate(R.layout.activity_feedbackform, container, false);
        name = (EditText) fragment1View.findViewById(R.id.name);
        email = (EditText) fragment1View.findViewById(R.id.email);
        message = (EditText) fragment1View.findViewById(R.id.msg);
        btn = (Button) fragment1View.findViewById(R.id.sendmail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_SEND,Uri.parse("mailto: " + "manthanraut16@gmail.com"));
                Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                emailIntent.setData(Uri.parse("mailto:manthanraut16@gmail.com"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, name.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                //intent.putExtra(Intent.EXTRA_SUBJECT, "Mail from " + name.getText().toString());
                //intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                //startActivity(intent);
            }
        });
        return fragment1View;
    }
}

