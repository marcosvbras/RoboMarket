package com.marcosvbras.robomarket.flows.dialog

import com.marcosvbras.robomarket.business.domain.RobotSale

interface DialogActions {

    fun onSave(robotSale: RobotSale)
    fun onRemove(robotSale: RobotSale)

}