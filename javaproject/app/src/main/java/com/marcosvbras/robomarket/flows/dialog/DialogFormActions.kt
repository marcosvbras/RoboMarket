package com.marcosvbras.robomarket.flows.dialog

import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity

interface DialogFormActions {

    fun onSave(itemRobotQuantity: ItemRobotQuantity)
    fun onRemove(itemRobotQuantity: ItemRobotQuantity)

}