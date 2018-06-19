package com.marcosvbras.robomarket.home.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.databinding.FragmentRobotsBinding;

public class RobotsFragment extends Fragment {

    private FragmentRobotsBinding fragmentRobotsBinding;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRobotsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_robots, container, false);
        view = fragmentRobotsBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
