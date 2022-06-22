package com.kt5.mobilefront1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText emailinput, pwinput;
    Button btnlogin, btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailinput = (EditText)findViewById(R.id.pwinput);
        pwinput = (EditText) findViewById(R.id.pwinput);
        btnlogin = (Button) findViewById(R.id.loginbtn);
        btnregister = (Button) findViewById(R.id.btnregister);
    }
}