package com.marcosvbras.robomarket.createsale.ui.adapter;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.home.OnRecyclerClick;

import java.util.ArrayList;
import java.util.List;

public class RobotSaleAdapter extends GroupAdapter {

    private List<Robot> listRobot;
    private OnRecyclerClick onRecyclerClick;

    public RobotSaleAdapter(OnRecyclerClick onRecyclerClick) {
        this.onRecyclerClick = onRecyclerClick;
    }

    public void updateItems(List<Robot> listItems) {
        this.clear();
        this.listRobot = listItems == null ? new ArrayList<>() : listItems;

        for(Robot robot : this.listRobot)
            add(new ItemSaleRobot(robot, onRecyclerClick));

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listRobot == null)
            return 0;

        return super.getItemCount();
    }
}
