package com.marcosvbras.robomarket.flows.dialog

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.databinding.FragmentDialogSaleBinding
import com.marcosvbras.robomarket.utils.Constants


class SaleDialogFragment : AppCompatDialogFragment(), DialogActions {

    private var fragmentBinding: FragmentDialogSaleBinding? = null
    private var dialogFormActions: DialogFormActions? = null
    private var itemRobotQuantity: ItemRobotQuantity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dialog_sale, container, false
        )

        val bundle = arguments
        setStyle(DialogFragment.STYLE_NORMAL, R.style.full_screen_dialog)

        fragmentBinding!!.viewModel = createViewModel()
        fragmentBinding!!.viewModel?.setActions(this)

        if (bundle != null && bundle.containsKey(Constants.Other.ROBOT_TAG)) {
            val robot = bundle.getParcelable<Robot>(Constants.Other.ROBOT_TAG)
            dialog.setTitle(robot!!.model)
        }

        return fragmentBinding!!.root
    }

    private fun createViewModel(): SaleDialogViewModel {
        return ViewModelProviders.of(
                this, SaleDialogViewModelFactory(dialogFormActions!!, itemRobotQuantity!!)
        ).get(SaleDialogViewModel::class.java)
    }

    override fun onFinished() {
        dialog.dismiss()
    }

    companion object {

        fun newInstance(itemRobotQuantity: ItemRobotQuantity, dialogFormActions: DialogFormActions): SaleDialogFragment {
            val saleDialogFragment = SaleDialogFragment()
            saleDialogFragment.dialogFormActions = dialogFormActions
            saleDialogFragment.itemRobotQuantity = itemRobotQuantity
            return saleDialogFragment
        }

    }
}