package com.marcosvbras.robomarket.flows.home.viewmodel

import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallbacks
import com.marcosvbras.robomarket.flows.home.ui.fragment.ProfileFragment
import com.marcosvbras.robomarket.flows.home.ui.fragment.RobotsFragment
import com.marcosvbras.robomarket.flows.home.ui.fragment.SalesFragment
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.viewmodels.BaseViewModel

class HomeViewModel(private val callback: HomeActivityCallbacks) : BaseViewModel() {

    private val salesFragment: SalesFragment = SalesFragment()
    private val robotsFragment: RobotsFragment = RobotsFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()

    init {
        callback.replaceFragment(salesFragment, Constants.Other.SALES_FRAGMENT_TAG)
    }

    fun onNavigationClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_sales -> callback.replaceFragment(salesFragment, Constants.Other.SALES_FRAGMENT_TAG)
            R.id.item_robots -> callback.replaceFragment(robotsFragment, Constants.Other.ROBOTS_FRAGMENT_TAG)
            R.id.item_profile -> callback.replaceFragment(profileFragment, Constants.Other.PROFILE_FRAGMENT_TAG)
        }

        return true
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {

    }
}