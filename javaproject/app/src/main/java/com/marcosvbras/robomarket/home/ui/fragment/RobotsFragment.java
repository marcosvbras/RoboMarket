package com.marcosvbras.robomarket.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentRobotsBinding;
import com.marcosvbras.robomarket.home.viewmodel.RobotsViewModel;
import com.marcosvbras.robomarket.home.viewmodel.RobotsViewModelFactory;
import com.marcosvbras.robomarket.utils.Constants;

public class RobotsFragment extends BaseFragment {

    private FragmentRobotsBinding fragmentRobotsBinding;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRobotsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_robots, container, false);
        view = fragmentRobotsBinding.getRoot();
        fragmentRobotsBinding.setViewModel(createViewModel());
        Log.d(Constants.Other.TAG, "onCreateView: ");
        return view;
    }

    private RobotsViewModel createViewModel() {
        return ViewModelProviders.of(this, new RobotsViewModelFactory(this))
                .get(RobotsViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
