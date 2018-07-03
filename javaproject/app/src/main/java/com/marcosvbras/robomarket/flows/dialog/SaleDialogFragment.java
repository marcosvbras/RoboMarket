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
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity;
import com.marcosvbras.robomarket.databinding.FragmentDialogSaleBinding;
import com.marcosvbras.robomarket.utils.Constants;

public class SaleDialogFragment extends AppCompatDialogFragment implements DialogActions {

    private FragmentDialogSaleBinding fragmentBinding;
    private View view;
    private DialogFormActions dialogFormActions;
    private ItemRobotQuantity itemRobotQuantity;

    public SaleDialogFragment() { }

    public static SaleDialogFragment newInstance(ItemRobotQuantity itemRobotQuantity, DialogFormActions dialogFormActions) {
        SaleDialogFragment saleDialogFragment = new SaleDialogFragment();
        saleDialogFragment.dialogFormActions = dialogFormActions;
        saleDialogFragment.itemRobotQuantity = itemRobotQuantity;
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
        fragmentBinding.getViewModel().setActions(this);

        if(bundle != null && bundle.containsKey(Constants.Other.ROBOT_TAG)) {
            Robot robot = bundle.getParcelable(Constants.Other.ROBOT_TAG);
            getDialog().setTitle(robot.getModel());
        }

        return view;
    }

    private SaleDialogViewModel createViewModel() {
        return ViewModelProviders.of(
                this, new SaleDialogViewModelFactory(dialogFormActions, itemRobotQuantity)
        ).get(SaleDialogViewModel.class);
    }

    @Override
    public void onFinished() {
        getDialog().dismiss();
    }
}
