package com.marcosvbras.robomarket.flows.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentSalesBinding;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.flows.createsale.ui.activity.CreateSaleActivity;
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback;
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.flows.home.viewmodel.SalesViewModel;
import com.marcosvbras.robomarket.flows.home.viewmodel.SalesViewModelFactory;
import com.marcosvbras.robomarket.interfaces.ActivityCallback;
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity;

import org.jetbrains.annotations.NotNull;

public class SalesFragment extends BaseFragment {

    private FragmentSalesBinding fragmentBinding;
    private HomeActivityCallback activityCallback;

    public static SalesFragment newInstance(@NonNull HomeActivityCallback activityCallback) {
        SalesFragment salesFragment = new SalesFragment();
        salesFragment.activityCallback = activityCallback;
        return salesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sales, container, false);
        setHasOptionsMenu(true);
        fragmentBinding.setViewModel(createViewModel());

        return fragmentBinding.getRoot();
    }

    private SalesViewModel createViewModel() {
        return ViewModelProviders.of(this, new SalesViewModelFactory(activityCallback))
                .get(SalesViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add:
                ((HomeActivity)getActivity()).openActivity(CreateSaleActivity.class, null,false);
                break;
            case R.id.menu_logout:
                App.getInstance().deleteCredentials();
                ((HomeActivity)getActivity()).openActivity(LoginActivity.class, null,true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
