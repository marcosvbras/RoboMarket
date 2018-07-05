package com.marcosvbras.robomarket.app

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback

open class BaseActivity : AppCompatActivity(), BaseActivityCallback {

    private var alertDialog: AlertDialog.Builder? = null

    override fun showDialogMessage(message: String) {
        showAlertDialog(message)
    }

    override fun showDialogMessage(message: Int) {
        showAlertDialog(getString(message))
    }

    private fun showAlertDialog(message: String) {
        if (alertDialog == null)
            alertDialog = AlertDialog.Builder(this)

        alertDialog!!
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show()
    }

    override fun showCustomAlertDialog(dialogFragment: DialogFragment) {
        val fragmentManager = supportFragmentManager
        dialogFragment.show(fragmentManager, dialogFragment.tag)
        fragmentManager.beginTransaction().commitAllowingStateLoss()
    }

    override fun openActivity(activity: Class<*>, bundle: Bundle?, finishCurrentActivity: Boolean) {
        val intent = Intent(this, activity)

        if (bundle != null)
            intent.putExtras(bundle)

        startActivity(intent)

        if (finishCurrentActivity)
            finish()
    }

    override fun openActivityWithAnimation(activity: Class<*>, bundle: Bundle?, finishCurrentActivity: Boolean, enterAnimation: Int, exitAnimation: Int) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (bundle != null)
            intent.putExtras(bundle)

        val opts = ActivityOptionsCompat.makeCustomAnimation(this, enterAnimation, exitAnimation)
        ActivityCompat.startActivity(this, intent, opts.toBundle())

        if (finishCurrentActivity)
            finish()
    }

    override fun openActivityForResult(activity: Class<*>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(this, activity)

        if (bundle != null)
            intent.putExtras(bundle)

        startActivityForResult(intent, requestCode)
    }

    override fun setToolbar(viewId: Int, displayHomeAsUpEnabled: Boolean) {
        setSupportActionBar(findViewById(viewId))
        supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
    }

    override fun finishCurrentActivity() {
        finish()
    }

    override fun setActivityResult(resultCode: Int) {
        setResult(resultCode)
    }

    override fun setActivityResult(resultCode: Int, intent: Intent) {
        setResult(resultCode, intent)
    }

    override fun setActivityResult(resultCode: Int, bundle: Bundle) {
        val intent = intent

        if (bundle != null)
            intent.putExtras(bundle)

        setResult(resultCode, intent)
    }

    override fun showSnackBar(message: String, length: Int) {
        Snackbar.make(findViewById(android.R.id.content), message, length).show()
    }

    override fun showSnackBar(message: Int, length: Int) {
        Snackbar.make(findViewById(android.R.id.content), getString(message), length).show()
    }
}