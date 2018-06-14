package com.marcosvbras.robomarket.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, LoginActivity.class));
    }
}
