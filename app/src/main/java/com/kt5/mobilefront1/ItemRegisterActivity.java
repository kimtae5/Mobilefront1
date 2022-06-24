package com.kt5.mobilefront1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ItemRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_register);
        this.setTitle("아이템추가");
    }
}