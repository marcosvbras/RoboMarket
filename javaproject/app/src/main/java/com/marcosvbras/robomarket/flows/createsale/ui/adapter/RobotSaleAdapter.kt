package com.marcosvbras.robomarket.flows.createsale.ui.adapter

import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.business.domain.RobotSale
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class RobotSaleAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    fun updateItems(listRoborSale: MutableList<RobotSale>) {
        clear()
        listRoborSale.forEach { add(ItemSaleRobot(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }

}