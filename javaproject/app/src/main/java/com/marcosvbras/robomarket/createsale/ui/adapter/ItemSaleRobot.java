package com.marcosvbras.robomarket.createsale.ui.adapter;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.ItemRobotSaleBinding;

public class ItemSaleRobot extends Item<ItemRobotSaleBinding> {

    private Robot robot;
    private int saleQuantity;

    public ItemSaleRobot(Robot robot) {
        this.robot = robot;
        saleQuantity = 0;
    }

    @Override
    public int getLayout() {
        return R.layout.item_robot_sale;
    }

    @Override
    public void bind(ItemRobotSaleBinding viewBinding, int position) {

    }
}
