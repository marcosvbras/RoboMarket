package com.marcosvbras.robomarket.flows.selectrobot.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.BaseActivity
import com.marcosvbras.robomarket.databinding.ActivitySelectRobotBinding
import com.marcosvbras.robomarket.flows.selectrobot.viewmodel.SelectRobotViewModel
import com.marcosvbras.robomarket.flows.selectrobot.viewmodel.SelectRobotViewModelFactory

class SelectRobotActivity : BaseActivity() {

    private var activityBinding: ActivitySelectRobotBinding? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_robot)
        setToolbar(R.id.top_toolbar, true)
        activityBinding?.viewModel = createViewModel()
        activityBinding?.executePendingBindings()
    }

    private fun createViewModel(): SelectRobotViewModel {
        return ViewModelProviders.of(this, SelectRobotViewModelFactory(this))
                .get(SelectRobotViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        menu.findItem(R.id.menu_add).isVisible = false
        menu.findItem(R.id.menu_logout).isVisible = false
        val menuItemSearch = menu.findItem(R.id.menu_search)
        searchView = menuItemSearch.actionView as SearchView
        searchView?.setOnQueryTextListener(onSearchListener())
        searchView?.setOnCloseListener {
            activityBinding?.viewModel?.listRobots(null)
            false
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun onSearchListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                activityBinding?.viewModel?.listRobots(newText)
                return true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}