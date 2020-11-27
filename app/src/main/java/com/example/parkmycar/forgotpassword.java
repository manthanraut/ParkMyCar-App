package com.example.parkmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {
    EditText email,pwd,confirmpwd;
    Button reset;
String email1,pwd1,confirmpwd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        confirmpwd = findViewById(R.id.confirmpassword);
        reset = findViewById(R.id.resetpassword);
        email1 = email.getText().toString();
        pwd1 = pwd.getText().toString();
        confirmpwd1 = confirmpwd.getText().toString();
        DatabaseHelper pdb = new DatabaseHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    //Save into DBS
                    pdb.updatedetails(email1, pwd1);
                    Toast.makeText(forgotpassword.this, "Password modified successfully", Toast.LENGTH_SHORT).show();
                    pdb.close();
                    startActivity(new Intent(forgotpassword.this, loginDriver.class));
                    finish();

            }
        });
    }
}