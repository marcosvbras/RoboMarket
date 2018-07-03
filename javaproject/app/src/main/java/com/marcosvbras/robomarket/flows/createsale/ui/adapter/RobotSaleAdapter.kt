package com.marcosvbras.robomarket.flows.createsale.ui.adapter

import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class RobotSaleAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    fun updateItems(listRoborRobotQuantity: MutableList<ItemRobotQuantity>) {
        clear()
        listRoborRobotQuantity.forEach { add(ItemSaleRobot(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }

}