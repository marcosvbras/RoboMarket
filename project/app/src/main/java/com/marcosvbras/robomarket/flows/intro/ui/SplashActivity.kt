package com.marcosvbras.robomarket.flows.intro.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivitySplashBinding
import com.marcosvbras.robomarket.flows.intro.viewmodel.SplashViewModel
import com.marcosvbras.robomarket.flows.intro.viewmodel.SplashViewModelFactory

class SplashActivity : BaseActivity() {

    private var activityBinding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        activityBinding?.viewModel = createViewModel()
        activityBinding?.executePendingBindings()
    }

    private fun createViewModel(): SplashViewModel {
        return ViewModelProviders.of(this, SplashViewModelFactory(this)).get(SplashViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        activityBinding?.viewModel?.authenticate()
    }
}