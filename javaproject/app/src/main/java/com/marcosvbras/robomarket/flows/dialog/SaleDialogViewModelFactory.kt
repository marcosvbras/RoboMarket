package com.marcosvbras.robomarket.flows.dialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity

@Suppress("UNCHECKED_CAST")
class SaleDialogViewModelFactory(private val dialogFormActions: DialogFormActions, private val itemRobotQuantity: ItemRobotQuantity) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaleDialogViewModel(itemRobotQuantity, dialogFormActions) as T
    }

}
