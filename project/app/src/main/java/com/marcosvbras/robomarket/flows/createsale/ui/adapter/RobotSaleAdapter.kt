package com.marcosvbras.robomarket.flows.createsale.ui.adapter

import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.business.beans.ItemRobotQuantity
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class RobotSaleAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    fun updateItems(listRobotRobotQuantity: MutableList<ItemRobotQuantity>) {
        clear()
        listRobotRobotQuantity.forEach { add(ItemSaleRobot(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }

}