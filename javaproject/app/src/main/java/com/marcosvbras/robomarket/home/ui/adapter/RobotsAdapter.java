package com.marcosvbras.robomarket.home.ui.adapter;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.home.OnRecyclerClick;

import java.util.ArrayList;
import java.util.List;

public class RobotsAdapter extends GroupAdapter {

    private List<Robot> listRobots;
    private OnRecyclerClick onRecyclerClick;

    public RobotsAdapter(OnRecyclerClick onRecyclerClick) {
        this.onRecyclerClick = onRecyclerClick;
    }

    public void updateItems(List<Robot> listRobots) {
        this.clear();
        this.listRobots = listRobots == null ? new ArrayList<>() : listRobots;

        for(Robot robot : this.listRobots)
            add(new ItemRobot(robot, onRecyclerClick));

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listRobots == null)
            return 0;

        return super.getItemCount();
    }
}
