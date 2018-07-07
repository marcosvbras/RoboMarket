package com.marcosvbras.robomarket.flows.createrobot.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.databinding.ActivityCreateRobotBinding
import com.marcosvbras.robomarket.flows.createrobot.viewmodel.CreateRobotViewModel
import com.marcosvbras.robomarket.flows.createrobot.viewmodel.CreateRobotViewModelFactory
import com.marcosvbras.robomarket.utils.Constants

class CreateRobotActivity : BaseActivity() {

    private var activityBinding: ActivityCreateRobotBinding? = null
    private var robot: Robot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey(Constants.Other.ROBOT_TAG))
            robot = intent.extras!!.getParcelable(Constants.Other.ROBOT_TAG)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_robot)
        activityBinding!!.viewModel = createRobotViewModel()
        activityBinding!!.executePendingBindings()
        setToolbar(R.id.top_toolbar, true)
    }

    private fun createRobotViewModel(): CreateRobotViewModel {
        return ViewModelProviders.of(this, CreateRobotViewModelFactory(this, robot))
                .get(CreateRobotViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}