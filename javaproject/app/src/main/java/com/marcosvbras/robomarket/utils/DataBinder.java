package com.marcosvbras.robomarket.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.home.ui.adapter.RobotsAdapter;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

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

    @BindingAdapter({"adapter", "data"})
    public static void bindRobotRecycler(RecyclerView recyclerView, RobotsAdapter robotsAdapter, List<Robot> list) {
        if(robotsAdapter != null) {
            recyclerView.setAdapter(robotsAdapter);
            robotsAdapter.updateItems(list);
        }
    }

}
