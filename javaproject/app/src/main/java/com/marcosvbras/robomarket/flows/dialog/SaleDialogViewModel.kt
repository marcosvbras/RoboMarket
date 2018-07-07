package com.marcosvbras.robomarket.flows.dialog

import android.databinding.Observable
import android.databinding.ObservableField
import android.text.TextUtils
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.beans.ItemRobotQuantity
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.app.BaseViewModel

class SaleDialogViewModel(private val itemRobotQuantity: ItemRobotQuantity?, private val dialogFormActions: DialogFormActions) : BaseViewModel() {

    val itemQuantity = ObservableField("1")
    val totalValue = ObservableField("Total: $ 0")
    val model = ObservableField<String>()
    val error = ErrorObservable()
    private var dialogActions: DialogActions? = null

    init {
        prepareData()
    }

    fun setActions(dialogActions: DialogActions) {
        this.dialogActions = dialogActions
    }

    private fun prepareData() {
        if (itemRobotQuantity != null) {
            model.set(itemRobotQuantity.robot.model)
            itemQuantity.addOnPropertyChangedCallback(onQuantityChanged())

            if (itemRobotQuantity.itemQuantity != 0) {
                itemQuantity.set(itemRobotQuantity.itemQuantity.toString())
            }
        }
    }

    private fun onQuantityChanged(): Observable.OnPropertyChangedCallback {
        return object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                updateTotalValue()
            }
        }
    }

    fun increaseQuantity() {
        if (TextUtils.isEmpty(itemQuantity.get()))
            itemQuantity.set("1")

        val quantityInt = Integer.parseInt(itemQuantity.get()!!)
        itemQuantity.set((quantityInt + 1).toString())
        updateTotalValue()
    }

    fun decreaseQuantity() {
        if (TextUtils.isEmpty(itemQuantity.get()) || itemQuantity.get() == "0") {
            itemQuantity.set("1")
            return
        }

        var quantityInt = Integer.parseInt(itemQuantity.get()!!)

        if (quantityInt > 1)
            quantityInt--

        itemQuantity.set(quantityInt.toString())
        updateTotalValue()
    }

    fun removeItem() {
        dialogFormActions.onRemove(itemRobotQuantity!!)
        finish()
    }

    fun saveItem() {
        if (checkStock()) {
            itemRobotQuantity!!.itemQuantity = if (TextUtils.isEmpty(itemQuantity.get())) 0 else Integer.valueOf(itemQuantity.get()!!)
            dialogFormActions.onSave(itemRobotQuantity)
            finish()
        }
    }

    private fun finish() {
        if (dialogActions != null)
            dialogActions!!.onFinished()
    }

    private fun updateTotalValue() {
        if (TextUtils.isEmpty(itemQuantity.get())) {
            totalValue.set("Total: $ 1")
            return
        }

        val value = Integer.parseInt(itemQuantity.get()!!) * itemRobotQuantity!!.robot.price!!
        totalValue.set("Total: $ " + value.toString())
        checkStock()
    }

    private fun checkStock(): Boolean {
        val quantityInt = if (TextUtils.isEmpty(itemQuantity.get())) 0 else Integer.parseInt(itemQuantity.get()!!)

        if (quantityInt > itemRobotQuantity!!.robot.quantity!!) {
            error.errorResource = R.string.error_stock_not_enough
            return false
        }

        return true
    }

    override fun onCleared() {
        super.onCleared()
        cleanupSubscriptions()
    }

    override fun cleanupSubscriptions() {

    }
}