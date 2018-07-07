package com.marcosvbras.robomarket.flows.login.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivityLoginBinding
import com.marcosvbras.robomarket.flows.login.viewmodel.LoginViewModel
import com.marcosvbras.robomarket.flows.login.viewmodel.LoginViewModelFactory

class LoginActivity : BaseActivity() {

    private var activityBinding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityBinding!!.viewModel = createViewModel()
        activityBinding!!.executePendingBindings()
    }

    private fun createViewModel(): LoginViewModel {
        return ViewModelProviders.of(this, LoginViewModelFactory(this))
                .get(LoginViewModel::class.java)
    }
}