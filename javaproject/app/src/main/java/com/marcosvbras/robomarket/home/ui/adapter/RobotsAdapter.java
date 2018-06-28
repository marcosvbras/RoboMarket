package com.marcosvbras.robomarket.home.ui.adapter;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.home.OnRecyclerClick;

import java.util.ArrayList;
import java.util.List;

public class RobotsAdapter extends GroupAdapter {

    private OnRecyclerClick onRecyclerClick;
    private List<Robot> listRobot;

    public RobotsAdapter(OnRecyclerClick onRecyclerClick) {
        this.onRecyclerClick = onRecyclerClick;
    }

    public void updateItems(List listItems) {
        this.clear();
        this.listRobot = listItems == null ? new ArrayList<>() : listItems;

        for(Robot robot : this.listRobot)
            add(new ItemRobot(robot, onRecyclerClick));

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listRobot == null)
            return 0;

        return super.getItemCount();
    }
}
