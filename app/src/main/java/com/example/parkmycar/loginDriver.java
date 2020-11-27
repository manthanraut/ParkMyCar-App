package com.example.parkmycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class loginDriver extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    DatabaseHelper databaseHelper;
    EditText email;
    Button loginButton;
    EditText password;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login_driver);
        this.awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        this.email = (EditText) findViewById(R.id.driveremail);
        this.password = (EditText) findViewById(R.id.driverpassword);
        this.loginButton = (Button) findViewById(R.id.login);
        this.databaseHelper = new DatabaseHelper(this);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.driveremail, Patterns.EMAIL_ADDRESS, (int) R.string.err_email);
        this.awesomeValidation.addValidation((Activity) this, (int) R.id.driverpassword, "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", (int) R.string.err_password);
        this.loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (loginDriver.this.awesomeValidation.validate()) {
                    if (loginDriver.this.databaseHelper.isLoginValid(loginDriver.this.email.getText().toString(), loginDriver.this.password.getText().toString())) {
                        loginDriver.this.startActivity(new Intent(loginDriver.this, HomeScreen.class));
                        Toast.makeText(loginDriver.this.getApplicationContext(), "Login Successful", 0).show();
                        return;
                    }
                    Toast.makeText(loginDriver.this.getApplicationContext(), "Incorrect Email or password", 0).show();
                    return;
                }
                Toast.makeText(loginDriver.this.getApplicationContext(), "Login Unsuccessful", 0).show();
            }
        });
    }

    public void toregister(View view) {
        Intent in = new Intent();
        in.setClass(this, registrationDriver.class);
        startActivity(in);
    }

    public void toforgetpassword(View view) {
        Intent in = new Intent();
        in.setClass(this, forgotpassword.class);
        startActivity(in);
    }
}
