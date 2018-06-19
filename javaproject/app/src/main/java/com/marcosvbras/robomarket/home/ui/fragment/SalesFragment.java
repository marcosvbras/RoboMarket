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
import com.marcosvbras.robomarket.databinding.FragmentSalesBinding;

public class SalesFragment extends Fragment {

    private FragmentSalesBinding fragmentSalesBinding;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSalesBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sales, container, false);
        view = fragmentSalesBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
