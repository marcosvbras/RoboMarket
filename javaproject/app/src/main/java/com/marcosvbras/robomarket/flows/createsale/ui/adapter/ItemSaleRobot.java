package com.marcosvbras.robomarket.flows.createsale.ui.adapter;

import android.view.View;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity;
import com.marcosvbras.robomarket.databinding.ItemRobotSaleBinding;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;

public class ItemSaleRobot extends Item<ItemRobotSaleBinding> implements View.OnClickListener {

    private ItemRobotQuantity itemRobotQuantity;
    private OnRecyclerClick onRecyclerClick;

    public ItemSaleRobot(ItemRobotQuantity itemRobotQuantity, OnRecyclerClick onRecyclerClick) {
        this.itemRobotQuantity = itemRobotQuantity;
        this.onRecyclerClick = onRecyclerClick;
    }

    @Override
    public int getLayout() {
        return R.layout.item_robot_sale;
    }

    @Override
    public void bind(ItemRobotSaleBinding viewBinding, int position) {
        viewBinding.setItemRobotQuantity(itemRobotQuantity);
        viewBinding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onRecyclerClick.onClick(itemRobotQuantity);
    }
}