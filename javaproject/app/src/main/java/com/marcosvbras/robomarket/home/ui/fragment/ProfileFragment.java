package com.marcosvbras.robomarket.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentProfileBinding;
import com.marcosvbras.robomarket.home.viewmodel.ProfileViewModel;
import com.marcosvbras.robomarket.home.viewmodel.ProfileViewModelFactory;

public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding fragmentProfileBinding;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentProfileBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);
        view = fragmentProfileBinding.getRoot();
        fragmentProfileBinding.setViewModel(createViewModel());
        fragmentProfileBinding.setUser(App.getInstance().getUser());
        setHasOptionsMenu(true);
        return view;
    }

    private ProfileViewModel createViewModel() {
        return ViewModelProviders.of(this, new ProfileViewModelFactory(this))
                .get(ProfileViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
