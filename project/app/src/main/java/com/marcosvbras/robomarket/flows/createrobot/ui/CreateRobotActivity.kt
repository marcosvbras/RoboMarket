package com.marcosvbras.robomarket.flows.createrobot.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.app.ROBOT_TAG
import com.marcosvbras.robomarket.business.beans.Robot
import com.marcosvbras.robomarket.databinding.ActivityCreateRobotBinding
import com.marcosvbras.robomarket.flows.createrobot.viewmodel.CreateRobotViewModel
import com.marcosvbras.robomarket.flows.createrobot.viewmodel.CreateRobotViewModelFactory

class CreateRobotActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        var robot: Robot? = null

        if (bundle != null && bundle.containsKey(ROBOT_TAG))
            robot = intent.extras!!.getParcelable(ROBOT_TAG)

        val activityBinding: ActivityCreateRobotBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_create_robot
        )

        setToolbar(R.id.top_toolbar, true)
        activityBinding.viewModel = createRobotViewModel(robot)
        activityBinding.executePendingBindings()
    }

    private fun createRobotViewModel(robot: Robot?): CreateRobotViewModel {
        return ViewModelProviders.of(this, CreateRobotViewModelFactory(this, robot))
                .get(CreateRobotViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}