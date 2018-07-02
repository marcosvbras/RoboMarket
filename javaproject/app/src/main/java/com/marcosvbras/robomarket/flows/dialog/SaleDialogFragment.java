package com.marcosvbras.robomarket.flows.dialog;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.domain.RobotSale;
import com.marcosvbras.robomarket.databinding.FragmentDialogSaleBinding;
import com.marcosvbras.robomarket.utils.Constants;

public class SaleDialogFragment extends AppCompatDialogFragment {

    private FragmentDialogSaleBinding fragmentBinding;
    private View view;
    private DialogActions dialogActions;
    private RobotSale robotSale;

    public SaleDialogFragment() { }

    public static SaleDialogFragment newInstance(RobotSale robotSale, DialogActions dialogActions) {
        SaleDialogFragment saleDialogFragment = new SaleDialogFragment();
        saleDialogFragment.dialogActions = dialogActions;
        saleDialogFragment.robotSale = robotSale;
        return saleDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dialog_sale, container, false
        );

        Bundle bundle = getArguments();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.full_screen_dialog);

        view = fragmentBinding.getRoot();
        fragmentBinding.setViewModel(createViewModel());

        if(bundle != null && bundle.containsKey(Constants.Other.ROBOT_TAG)) {
            Robot robot = bundle.getParcelable(Constants.Other.ROBOT_TAG);
            getDialog().setTitle(robot.getModel());
        }

        return view;
    }

    private SaleDialogViewModel createViewModel() {
        return ViewModelProviders.of(
                this, new SaleDialogViewModelFactory(dialogActions, robotSale)
        ).get(SaleDialogViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
