package com.marcosvbras.robomarket.flows.createsale.ui.adapter

import com.genius.groupie.Item
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.domain.RobotSale
import com.marcosvbras.robomarket.databinding.ItemRobotSaleBinding
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class ItemSaleRobot(val robotSale: RobotSale, val onRecyclerClick: OnRecyclerClick) : Item<ItemRobotSaleBinding>() {

    override fun getLayout(): Int = R.layout.item_robot_sale

    override fun bind(viewBinding: ItemRobotSaleBinding?, position: Int) {
        viewBinding?.robotSale = robotSale
    }

}