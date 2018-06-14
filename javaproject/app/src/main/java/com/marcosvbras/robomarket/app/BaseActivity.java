package com.marcosvbras.robomarket.app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class BaseActivity extends AppCompatActivity implements BaseActivityCallback {

    private AlertDialog.Builder alertDialog;

    @Override
    public void showErrorDialog(String message) {
        showDialog(message);
    }

    @Override
    public void showErrorDialog(int message) {
        showDialog(getString(message));
    }

    private void showDialog(String message) {
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(this);

        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    @Override
    public void openActivity(Class<?> activity, boolean finishCurrentActivity) {
        startActivity(new Intent(this, activity));

        if(finishCurrentActivity)
            finish();
    }

    @Override
    public void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity) {
        Intent intent = new Intent(this, activity);

        if(bundle != null)
            intent.putExtras(bundle);

        startActivity(intent);

        if(finishCurrentActivity)
            finish();
    }

    @Override
    public void setToolbar(int viewId, boolean displayHomeAsUpEnabled) {
        setSupportActionBar(findViewById(viewId));
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }
}
