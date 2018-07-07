package com.marcosvbras.robomarket.flows.home.viewmodel

import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.PROFILE_FRAGMENT_TAG
import com.marcosvbras.robomarket.app.ROBOTS_FRAGMENT_TAG
import com.marcosvbras.robomarket.app.SALES_FRAGMENT_TAG
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.ui.fragment.ProfileFragment
import com.marcosvbras.robomarket.flows.home.ui.fragment.RobotsFragment
import com.marcosvbras.robomarket.flows.home.ui.fragment.SalesFragment
import com.marcosvbras.robomarket.app.BaseViewModel

class HomeViewModel(private val callback: HomeActivityCallback) : BaseViewModel() {

    private val salesFragment: SalesFragment = SalesFragment.newInstance(callback)
    private val robotsFragment: RobotsFragment = RobotsFragment.newInstance(callback)
    private val profileFragment: ProfileFragment = ProfileFragment.newInstance(callback)

    init {
        callback.replaceFragment(salesFragment, SALES_FRAGMENT_TAG)
    }

    fun onNavigationClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_sales -> callback.replaceFragment(salesFragment, SALES_FRAGMENT_TAG)
            R.id.item_robots -> callback.replaceFragment(robotsFragment, ROBOTS_FRAGMENT_TAG)
            R.id.item_profile -> callback.replaceFragment(profileFragment, PROFILE_FRAGMENT_TAG)
        }

        return true
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {}
}