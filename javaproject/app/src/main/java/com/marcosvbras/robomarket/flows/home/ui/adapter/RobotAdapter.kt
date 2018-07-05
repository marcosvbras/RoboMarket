package com.marcosvbras.robomarket.flows.home.ui.adapter

import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import java.util.ArrayList

class RobotAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    private var listRobot: List<Robot>? = null

    fun updateItems(listItems: List<Robot>?) {
        clear()
        listRobot = listItems ?: ArrayList()
        listItems?.forEach { add(ItemRobot(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }
}