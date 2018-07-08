package com.marcosvbras.robomarket.flows.createsale.ui.adapter

import android.view.View
import com.genius.groupie.Item
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.beans.ItemRobotQuantity
import com.marcosvbras.robomarket.databinding.ItemRobotSaleBinding
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class ItemSaleRobot(private val itemRobotQuantity: ItemRobotQuantity, private val onRecyclerClick: OnRecyclerClick) : Item<ItemRobotSaleBinding>(), View.OnClickListener {

    override fun getLayout(): Int {
        return R.layout.item_robot_sale
    }

    override fun bind(viewBinding: ItemRobotSaleBinding, position: Int) {
        viewBinding.itemRobotQuantity = itemRobotQuantity
        viewBinding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        onRecyclerClick.onClick(itemRobotQuantity)
    }

}