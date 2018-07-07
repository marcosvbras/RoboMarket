package com.marcosvbras.robomarket.flows.home.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.BaseFragment
import com.marcosvbras.robomarket.databinding.FragmentProfileBinding
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.viewmodel.ProfileViewModel
import com.marcosvbras.robomarket.flows.home.viewmodel.ProfileViewModelFactory
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity

class ProfileFragment : BaseFragment() {

    private var fragmentBinding: FragmentProfileBinding? = null
    private var activityCallback: HomeActivityCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false)
        setHasOptionsMenu(true)
        fragmentBinding!!.viewModel = createViewModel()
        showInfo()
        return fragmentBinding!!.root
    }

    private fun createViewModel(): ProfileViewModel {
        return ViewModelProviders.of(this, ProfileViewModelFactory(activityCallback!!))
                .get(ProfileViewModel::class.java)
    }

    fun showInfo() {
        fragmentBinding!!.user = App.instance.user
    }

    override fun onResume() {
        super.onResume()
        showInfo()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_home, menu)
        menu!!.findItem(R.id.menu_add).isVisible = false
        menu.findItem(R.id.menu_search).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menu_logout) {
            App.instance.deleteCredentials()
            activityCallback!!.openActivity(LoginActivity::class.java, null, true)
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(activityCallback: HomeActivityCallback): ProfileFragment {
            val profileFragment = ProfileFragment()
            profileFragment.activityCallback = activityCallback
            return profileFragment
        }
    }
}