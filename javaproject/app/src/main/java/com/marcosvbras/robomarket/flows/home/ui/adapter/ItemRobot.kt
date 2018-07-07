package com.marcosvbras.robomarket.flows.home.ui.adapter

import android.view.View
import com.genius.groupie.Item
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.beans.Robot
import com.marcosvbras.robomarket.databinding.ItemRobotBinding
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class ItemRobot(private val robot: Robot, private val onRecyclerClick: OnRecyclerClick) : Item<ItemRobotBinding>(), View.OnClickListener {

    override fun getLayout(): Int {
        return R.layout.item_robot
    }

    override fun bind(viewBinding: ItemRobotBinding, position: Int) {
        viewBinding.robot = robot
        viewBinding.listItem.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        onRecyclerClick.onClick(robot)
    }
}