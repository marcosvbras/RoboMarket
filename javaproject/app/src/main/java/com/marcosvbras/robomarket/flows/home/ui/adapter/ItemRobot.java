package com.marcosvbras.robomarket.flows.home.ui.adapter;

import android.view.View;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.ItemRobotBinding;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;

public class ItemRobot extends Item<ItemRobotBinding> implements View.OnClickListener {

    private Robot robot;
    private OnRecyclerClick onRecyclerClick;

    public ItemRobot(Robot robot, OnRecyclerClick onRecyclerClick) {
        this.robot = robot;
        this.onRecyclerClick = onRecyclerClick;
    }

    @Override
    public int getLayout() {
        return R.layout.item_robot;
    }

    @Override
    public void bind(ItemRobotBinding viewBinding, int position) {
        viewBinding.setRobot(robot);
        viewBinding.listItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onRecyclerClick.onClick(robot);
    }
}
