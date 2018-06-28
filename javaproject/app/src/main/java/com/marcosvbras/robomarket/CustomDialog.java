package com.marcosvbras.robomarket;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import io.reactivex.subjects.PublishSubject;

public class CustomDialog<T extends ViewDataBinding, U extends ViewModel> {

    private AlertDialog alertDialog;
    private T binding;
    private U viewModel;
    private Context context;
    public final PublishSubject<String> publishSubject = PublishSubject.create();

    public CustomDialog(int layoutId, Context context) {
        this.context = context;
        alertDialog = new AlertDialog.Builder(context).create();

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), layoutId, null, false
        );

        alertDialog.setView(binding.getRoot());
        publishSubject.onNext("Ola");
    }

    public void setTitle(int title) {
        alertDialog.setTitle(context.getString(title));
    }

    public void setTitle(String title) {
        alertDialog.setTitle(title);
    }

    public T getBinding() {
        return binding;
    }

    public U getViewModel() {
        return viewModel;
    }

    public void show() {
        alertDialog.show();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }
}
