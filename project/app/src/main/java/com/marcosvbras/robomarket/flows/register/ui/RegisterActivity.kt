package com.marcosvbras.robomarket.flows.register.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivityRegisterBinding
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModel
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModelFactory

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBinding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        setToolbar(R.id.top_toolbar, true)
        activityBinding.viewModel = createViewModel()
        activityBinding.executePendingBindings()
    }

    private fun createViewModel(): RegisterViewModel {
        return ViewModelProviders.of(this, RegisterViewModelFactory(this))
                .get(RegisterViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}