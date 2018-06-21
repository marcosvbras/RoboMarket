package com.marcosvbras.robomarket.home.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentSalesBinding;
import com.marcosvbras.robomarket.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.login.ui.LoginActivity;

public class SalesFragment extends BaseFragment {

    private FragmentSalesBinding fragmentSalesBinding;
    private View view;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSalesBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sales, container, false);
        view = fragmentSalesBinding.getRoot();
        setHasOptionsMenu(true);
        return view;
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
//                ((HomeActivity)getActivity()).openActivity(CreateRobotActivity.class, false);
                break;
            case R.id.menu_logout:
                App.getInstance().deleteCredentials();
                ((HomeActivity)getActivity()).openActivity(LoginActivity.class, true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
