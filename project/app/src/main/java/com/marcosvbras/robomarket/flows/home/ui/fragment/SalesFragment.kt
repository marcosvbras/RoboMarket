package com.marcosvbras.robomarket.flows.home.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.BaseFragment
import com.marcosvbras.robomarket.databinding.FragmentSalesBinding
import com.marcosvbras.robomarket.flows.createsale.ui.activity.CreateSaleActivity
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.flows.home.viewmodel.SalesViewModel
import com.marcosvbras.robomarket.flows.home.viewmodel.SalesViewModelFactory
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity

class SalesFragment : BaseFragment() {

    private var fragmentBinding: FragmentSalesBinding? = null
    private var activityCallback: HomeActivityCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sales, container, false)
        setHasOptionsMenu(true)
        fragmentBinding?.viewModel = createViewModel()

        return fragmentBinding?.root
    }

    private fun createViewModel(): SalesViewModel {
        return ViewModelProviders.of(this, SalesViewModelFactory(activityCallback!!))
                .get(SalesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.menu_home, menu)
        menu?.findItem(R.id.menu_search)?.isVisible = false

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> (activity as HomeActivity).openActivity(CreateSaleActivity::class.java, null, false)
            R.id.menu_logout -> {
                App.instance.deleteCredentials()
                activityCallback?.openActivity(LoginActivity::class.java, null, true)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(activityCallback: HomeActivityCallback): SalesFragment {
            val salesFragment = SalesFragment()
            salesFragment.activityCallback = activityCallback

            return salesFragment
        }

    }

}