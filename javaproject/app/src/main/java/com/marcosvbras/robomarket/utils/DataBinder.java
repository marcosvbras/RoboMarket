package com.marcosvbras.robomarket.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.genius.groupie.GroupAdapter;
import com.marcosvbras.robomarket.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void setError(MaterialEditText editText, ErrorObservable errorObservable) {
        if(errorObservable.hasError()) {
            if(errorObservable.getErrorResource() != null)
                editText.setError(editText.getContext().getString(errorObservable.getErrorResource()));
            else if(errorObservable.getErrorString() != null)
                editText.setError(errorObservable.getErrorString());

            errorObservable.clear();
        }
    }

    @BindingAdapter(value="url_image")
    public static void setAvatar(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_robot_grey600_48dp);
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("selection")
    public static void setSelection(Spinner spinner, String item) {
        SpinnerAdapter spinnerAdapter = spinner.getAdapter();

        for(int i = 0; i < spinnerAdapter.getCount(); i++) {
            if (spinnerAdapter.getItem(i).toString().equals(item)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void bindAdapter(RecyclerView recyclerView, GroupAdapter adapter) {
        if(adapter != null)
            recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"mask"})
    public static void addMask(EditText editText, String mask) {
        if(!TextUtils.isEmpty(mask))
            editText.addTextChangedListener(new MaskWatcher(mask, editText));
    }

}
