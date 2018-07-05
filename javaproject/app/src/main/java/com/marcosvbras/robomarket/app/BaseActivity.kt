package com.marcosvbras.robomarket.app

import android.content.Intent
import android.os.Bundle
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

    override fun openActivity(activity: Class<*>, finishCurrentActivity: Boolean) {
        startActivity(Intent(this, activity))

        if (finishCurrentActivity)
            finish()
    }

    override fun openActivity(activity: Class<*>, bundle: Bundle, finishCurrentActivity: Boolean) {
        val intent = Intent(this, activity)

        if (bundle != null)
            intent.putExtras(bundle)

        startActivity(intent)

        if (finishCurrentActivity)
            finish()
    }

    override fun openActivityForResult(activity: Class<*>, bundle: Bundle, requestCode: Int) {
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
}