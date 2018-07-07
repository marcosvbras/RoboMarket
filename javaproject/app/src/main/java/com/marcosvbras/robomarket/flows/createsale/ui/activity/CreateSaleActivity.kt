package com.marcosvbras.robomarket.flows.createsale.ui.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.app.ROBOT_TAG
import com.marcosvbras.robomarket.app.SELECT_ROBOT_REQUEST_CODE
import com.marcosvbras.robomarket.business.beans.Robot
import com.marcosvbras.robomarket.databinding.ActivityCreateSaleBinding
import com.marcosvbras.robomarket.flows.createsale.viewmodel.CreateSaleViewModel
import com.marcosvbras.robomarket.flows.createsale.viewmodel.CreateSaleViewModelFactory

class CreateSaleActivity : BaseActivity() {

    private var activityBinding: ActivityCreateSaleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_sale)
        activityBinding!!.viewModel = createViewModel()
        activityBinding!!.executePendingBindings()
        setToolbar(R.id.top_toolbar, true)
    }

    private fun createViewModel(): CreateSaleViewModel {
        return ViewModelProviders.of(this, CreateSaleViewModelFactory(this)).get(CreateSaleViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == SELECT_ROBOT_REQUEST_CODE && resultCode == Activity.RESULT_OK)
            activityBinding!!.viewModel?.addRobot(data.extras!!.getParcelable<Robot>(ROBOT_TAG))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

}