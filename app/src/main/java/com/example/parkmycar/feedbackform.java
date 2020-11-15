package com.example.parkmycar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class feedbackform extends AppCompatActivity {
    EditText name,email,message;
    Button btn;
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_feedbackform);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        message=(EditText)findViewById(R.id.msg);
        btn=(Button)findViewById(R.id.sendmail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto: "+"manthanraut16@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Mail from "+name.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
                startActivity(intent);
            }
        });
    }
}
