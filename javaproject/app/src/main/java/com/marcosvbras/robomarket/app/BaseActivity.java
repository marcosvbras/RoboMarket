package com.marcosvbras.robomarket.app;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class BaseActivity extends AppCompatActivity implements BaseActivityCallback {

    private AlertDialog.Builder alertDialog;

    @Override
    public void showDialogMessage(String message) {
        showAlertDialog(message);
    }

    @Override
    public void showDialogMessage(int message) {
        showAlertDialog(getString(message));
    }

    private void showAlertDialog(String message) {
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(this);

        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    @Override
    public void showCustomAlertDialog(DialogFragment dialogFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogFragment.show(fragmentManager, dialogFragment.getTag());
        fragmentManager.beginTransaction().commitAllowingStateLoss();
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
    public void openActivityForResult(Class<?> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, activity);

        if(bundle != null)
            intent.putExtras(bundle);

        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setToolbar(int viewId, boolean displayHomeAsUpEnabled) {
        setSupportActionBar(findViewById(viewId));
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }

    @Override
    public void finishCurrentActivity() {
        finish();
    }

    @Override
    public void setActivityResult(int resultCode) {
        setResult(resultCode);
    }

    @Override
    public void setActivityResult(int resultCode, Intent intent) {
        setResult(resultCode, intent);
    }

    @Override
    public void setActivityResult(int resultCode, Bundle bundle) {
        Intent intent = getIntent();

        if(bundle != null)
            intent.putExtras(bundle);

        setResult(resultCode, intent);
    }
}
