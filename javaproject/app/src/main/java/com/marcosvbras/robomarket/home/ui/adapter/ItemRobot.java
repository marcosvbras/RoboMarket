package com.marcosvbras.robomarket.home.ui.adapter;

import android.view.View;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.ItemRobotBinding;

public class ItemRobot extends Item<ItemRobotBinding> {

    private Robot robot;
    private View.OnClickListener onClickListener;

    public ItemRobot(Robot robot, View.OnClickListener onClickListener) {
        this.robot = robot;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getLayout() {
        return R.layout.item_robot;
    }

    @Override
    public void bind(ItemRobotBinding viewBinding, int position) {
        viewBinding.setRobot(robot);
        viewBinding.listItem.setOnClickListener(onClickListener);
    }
}
