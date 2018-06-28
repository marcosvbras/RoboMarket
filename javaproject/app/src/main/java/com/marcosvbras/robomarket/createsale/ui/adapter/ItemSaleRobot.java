package com.marcosvbras.robomarket.createsale.ui.adapter;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.ItemRobotSaleBinding;
import com.marcosvbras.robomarket.home.OnRecyclerClick;

public class ItemSaleRobot extends Item<ItemRobotSaleBinding> {

    private OnRecyclerClick onRecyclerClick;
    private Robot robot;
    private int saleQuantity;

    public ItemSaleRobot(Robot robot, OnRecyclerClick onRecyclerClick) {
        this.robot = robot;
        this.onRecyclerClick = onRecyclerClick;
        saleQuantity = 0;
    }

    @Override
    public int getLayout() {
        return R.layout.item_robot_sale;
    }

    @Override
    public void bind(ItemRobotSaleBinding viewBinding, int position) {
        viewBinding.setRobot(robot);
    }
}
