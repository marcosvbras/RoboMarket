package com.marcosvbras.robomarket.flows.home.ui.fragment

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.*
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.BaseFragment
import com.marcosvbras.robomarket.app.NEW_ROBOT_REQUEST_CODE
import com.marcosvbras.robomarket.databinding.FragmentRobotsBinding
import com.marcosvbras.robomarket.flows.createrobot.ui.CreateRobotActivity
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.viewmodel.RobotsViewModel
import com.marcosvbras.robomarket.flows.home.viewmodel.RobotsViewModelFactory
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity

class RobotsFragment : BaseFragment() {

    private var fragmentBinding: FragmentRobotsBinding? = null
    private var searchView: SearchView? = null
    private var activityCallback: HomeActivityCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_robots, container, false)
        fragmentBinding?.viewModel = createViewModel()
        setHasOptionsMenu(true)

        return fragmentBinding?.root
    }

    private fun createViewModel(): RobotsViewModel {
        return ViewModelProviders.of(this, RobotsViewModelFactory(activityCallback!!))
                .get(RobotsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.menu_home, menu)
        val menuItemSearch = menu?.findItem(R.id.menu_search)
        searchView = menuItemSearch?.actionView as SearchView
        searchView?.setOnQueryTextListener(onSearchListener())
        searchView?.setOnCloseListener {
            fragmentBinding?.viewModel?.listRobots(null)
            false
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSearchListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                fragmentBinding?.viewModel?.listRobots(newText)
                return true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> startActivityForResult(
                    Intent(activity?.baseContext, CreateRobotActivity::class.java),
                    NEW_ROBOT_REQUEST_CODE
            )
            R.id.menu_logout -> {
                App.instance.deleteCredentials()
                activityCallback?.openActivity(LoginActivity::class.java, null, true)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == NEW_ROBOT_REQUEST_CODE && resultCode == RESULT_OK)
            fragmentBinding?.viewModel?.listRobots(null)
    }

    companion object {

        fun newInstance(activityCallback: HomeActivityCallback): RobotsFragment {
            val robotsFragment = RobotsFragment()
            robotsFragment.activityCallback = activityCallback

            return robotsFragment
        }
    }

}