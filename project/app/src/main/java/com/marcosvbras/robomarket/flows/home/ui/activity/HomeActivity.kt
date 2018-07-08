package com.marcosvbras.robomarket.flows.home.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivityHomeBinding
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.viewmodel.HomeViewModel
import com.marcosvbras.robomarket.flows.home.viewmodel.HomeViewModelFactory

class HomeActivity : BaseActivity(), HomeActivityCallback {

    private var fragmentManager: FragmentManager? = null
    private var activeFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBinding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setToolbar(R.id.top_toolbar, false)
        fragmentManager = supportFragmentManager
        activityBinding.viewModel = createViewModel()
        activityBinding.executePendingBindings()
    }

    private fun createViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, HomeViewModelFactory(this)).get(HomeViewModel::class.java)
    }

    override fun replaceFragment(fragment: Fragment, tag: String) {
        val frag = fragmentManager?.findFragmentByTag(tag)
        val transaction = fragmentManager?.beginTransaction()

        if (frag == null)
            transaction?.add(R.id.conteinerFrag, fragment, tag)

        if (activeFragment != null && activeFragment !== fragment) {
            transaction?.hide(activeFragment)
            transaction?.show(fragment)
        }

        activeFragment = fragment
        transaction?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)?.commit()
    }

}