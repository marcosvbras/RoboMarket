package com.marcosvbras.robomarket.home.ui.adapter;

import android.view.View;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.robomarket.business.domain.Robot;

import java.util.ArrayList;
import java.util.List;

public class RobotsAdapter extends GroupAdapter {

    private List<Robot> listRobots;
    private View.OnClickListener onClickListener;

    public RobotsAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void updateItems(List<Robot> listRobots) {
        this.clear();
        this.listRobots = listRobots == null ? new ArrayList<>() : listRobots;

        for(Robot robot : this.listRobots)
            add(new ItemRobot(robot, onClickListener));

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listRobots == null)
            return 0;

        return super.getItemCount();
    }
}
