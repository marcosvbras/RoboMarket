package com.marcosvbras.robomarket.flows.profile.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivityEditProfileBinding
import com.marcosvbras.robomarket.flows.profile.viewmodel.EditProfileViewModel
import com.marcosvbras.robomarket.flows.profile.viewmodel.EditProfileViewModelFactory

class EditProfileActivity : BaseActivity() {

    private var activityBinding: ActivityEditProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        activityBinding!!.viewModel = createViewModel()
        activityBinding!!.executePendingBindings()
        setToolbar(R.id.top_toolbar, true)
    }

    private fun createViewModel(): EditProfileViewModel {
        return ViewModelProviders.of(this, EditProfileViewModelFactory(this))
                .get(EditProfileViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}