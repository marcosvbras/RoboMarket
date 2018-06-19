package com.marcosvbras.robomarket.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void setError(MaterialEditText editText, ErrorObservable errorObservable) {
        if(errorObservable.hasErrorSetted()) {
            if(errorObservable.getIntError() != null)
                editText.setError(editText.getContext().getString(errorObservable.getIntError()));
            else if(errorObservable.get() != null)
                editText.setError(errorObservable.get());

            errorObservable.clear();
        }
    }

    @BindingAdapter(value="url_image")
    public static void setAvatar(CircleImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

//
//    @BindingAdapter({"adapter", "data"})
//    public static void bindData(RecyclerView recyclerView, EnterpriseAdapter adapter, List<Enterprise> list) {
//        recyclerView.setAdapter(adapter);
//        adapter.updateItems(list);
//    }
}
