package com.marcosvbras.robomarket.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.DialogActions;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.SaleDialogViewModel;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.createsale.ui.activity.CreateSaleActivity;
import com.marcosvbras.robomarket.databinding.FragmentSalesBinding;
import com.marcosvbras.robomarket.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.home.viewmodel.SalesViewModel;
import com.marcosvbras.robomarket.home.viewmodel.SalesViewModelFactory;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.login.ui.LoginActivity;

public class SalesFragment extends BaseFragment implements BaseActivityCallback {

    private FragmentSalesBinding fragmentSalesBinding;
    private View view;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSalesBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sales, container, false);
        view = fragmentSalesBinding.getRoot();
        fragmentSalesBinding.setViewModel(createViewModel());
        setHasOptionsMenu(true);
        return view;
    }

    private SalesViewModel createViewModel() {
        return ViewModelProviders.of(this, new SalesViewModelFactory(this))
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
        MenuItem menuItemSearch = menu.findItem(R.id.menu_search);
        searchView = (SearchView)menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
//            fragmentSalesBinding.getViewModel().listRobots(null);
            return false;
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private SearchView.OnQueryTextListener onSearchListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                fragmentRobotsBinding.getViewModel().listRobots(newText);
                return true;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add:
                ((HomeActivity)getActivity()).openActivity(CreateSaleActivity.class, false);
                break;
            case R.id.menu_logout:
                App.getInstance().deleteCredentials();
                ((HomeActivity)getActivity()).openActivity(LoginActivity.class, true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDialogMessage(String message) {

    }

    @Override
    public void showDialogMessage(int message) {

    }

    @Override
    public void openActivity(Class<?> activity, boolean finishCurrentActivity) {

    }

    @Override
    public void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity) {

    }

    @Override
    public void openActivityForResult(Class<?> activity, Bundle bundle, int requestCode) {

    }

    @Override
    public void setToolbar(int viewId, boolean displayHomeAsUpEnabled) {

    }

    @Override
    public void finishCurrentActivity() {

    }

    @Override
    public void setActivityResult(int resultCode) {

    }

    @Override
    public void setActivityResult(int resultCode, Intent intent) {

    }

    @Override
    public void setActivityResult(int resultCode, Bundle bundle) {

    }

    @Override
    public void showCustomAlertDialog(Object object, DialogActions actions) {

    }
}
