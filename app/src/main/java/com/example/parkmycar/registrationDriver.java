package com.example.parkmycar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class registrationDriver extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    DatabaseHelper databaseHelper;
    EditText newDriverConfirmPassword;
    EditText newDriverEmail;
    EditText newDriverName;
    EditText newDriverPassword;
    EditText newDrivermobile;
    Button registerbutton;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_registration_driver);
        this.awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        this.newDriverName = (EditText) findViewById(R.id.drivername);
        this.newDriverEmail = (EditText) findViewById(R.id.newdriveremail);
        this.newDrivermobile = (EditText) findViewById(R.id.mobilenum);
        this.newDriverPassword = (EditText) findViewById(R.id.newdriverpassword2);
        this.newDriverConfirmPassword = (EditText) findViewById(R.id.confirmpassword);
        this.registerbutton = (Button) findViewById(R.id.register);
        this.databaseHelper = new DatabaseHelper(this);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.drivername, "[a-zA-Z\\s]+", (int) R.string.err_name);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.mobilenum, "[7-9][0-9]{9}", (int) R.string.err_tel);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.newdriveremail, Patterns.EMAIL_ADDRESS, (int) R.string.err_email);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.newdriverpassword2, "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", (int) R.string.err_password);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.confirmpassword, (int) R.id.newdriverpassword2, (int) R.string.err_password_confirmation);
        this.registerbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (registrationDriver.this.awesomeValidation.validate()) {
                    String aa = registrationDriver.this.newDriverName.getText().toString();
                    String bb = registrationDriver.this.newDriverEmail.getText().toString();
                    String cc = registrationDriver.this.newDrivermobile.getText().toString();
                    String dd = registrationDriver.this.newDriverPassword.getText().toString();
                    String ee = registrationDriver.this.newDriverConfirmPassword.getText().toString();
                    if (aa.length() > 1) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", aa);
                        contentValues.put(NotificationCompat.CATEGORY_EMAIL, bb);
                        contentValues.put("mobno", cc);
                        contentValues.put("password", dd);
                        registrationDriver.this.databaseHelper.insertUser(contentValues);
                        Toast.makeText(registrationDriver.this.getApplicationContext(), "Registration Successful", 0).show();
                        registrationDriver.this.startActivity(new Intent(registrationDriver.this, loginDriver.class));
                        return;
                    }
                    Toast.makeText(registrationDriver.this.getApplicationContext(), "Invalid Values", 0).show();
                    return;
                }
                Toast.makeText(registrationDriver.this.getApplicationContext(), "Invalid", 0).show();
            }
        });
    }

    public void tologin(View view) {
        Intent in = new Intent();
        in.setClass(this, loginDriver.class);
        startActivity(in);
    }
}
