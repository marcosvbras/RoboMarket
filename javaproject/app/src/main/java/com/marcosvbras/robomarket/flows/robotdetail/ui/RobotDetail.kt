package com.marcosvbras.robomarket.flows.robotdetail.ui

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.app.EDIT_ROBOT_REQUEST_CODE
import com.marcosvbras.robomarket.app.ROBOT_TAG
import com.marcosvbras.robomarket.business.beans.Robot
import com.marcosvbras.robomarket.databinding.ActivityRobotDetailBinding
import com.marcosvbras.robomarket.flows.robotdetail.viewmodel.RobotDetailViewModel
import com.marcosvbras.robomarket.flows.robotdetail.viewmodel.RobotDetailViewModelFactory

class RobotDetailActivity : BaseActivity() {

    private var activityBinding: ActivityRobotDetailBinding? = null
    private var robot: Robot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        robot = intent.extras!!.getParcelable(ROBOT_TAG)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_robot_detail)
        activityBinding!!.viewModel = createViewModel()
        activityBinding!!.executePendingBindings()
        setToolbar(R.id.top_toolbar, true)
    }

    private fun createViewModel(): RobotDetailViewModel {
        return ViewModelProviders.of(this, RobotDetailViewModelFactory(this))
                .get(RobotDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        activityBinding!!.viewModel?.showData(robot)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == EDIT_ROBOT_REQUEST_CODE && resultCode == Activity.RESULT_OK)
            robot = data.extras!!.getParcelable(ROBOT_TAG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}