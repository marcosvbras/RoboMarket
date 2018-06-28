package com.marcosvbras.robomarket;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.FragmentDialogSaleBinding;
import com.marcosvbras.robomarket.utils.Constants;

public class SaleDialogFragment extends AppCompatDialogFragment {

    private FragmentDialogSaleBinding fragmentBinding;
    private View view;

    public SaleDialogFragment() { }

    public static SaleDialogFragment newInstance() {
        SaleDialogFragment saleDialogFragment = new SaleDialogFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(Constants.Other.ROBOT_TAG, robot);
//        saleDialogFragment.setArguments(bundle);
        return saleDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dialog_sale, container, false
        );
        view = fragmentBinding.getRoot();
        fragmentBinding.setViewModel(ViewModelProviders.of(this).get(SaleDialogViewModel.class));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
