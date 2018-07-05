package com.marcosvbras.robomarket.utils

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.R
import com.rengwuxian.materialedittext.MaterialEditText

@BindingAdapter("setError")
fun setError(editText: MaterialEditText, errorObservable: ErrorObservable) {
    if (errorObservable.hasError()) {
        if (errorObservable.errorResource != null)
            editText.error = editText.context.getString(errorObservable.errorResource!!)
        else if (errorObservable.errorString != null)
            editText.error = errorObservable.errorString

        errorObservable.clear()
    }
}

@BindingAdapter("url_image")
fun setAvatar(imageView: ImageView, url: String) {
    val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_robot_grey600_48dp)
    Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)
}

@BindingAdapter("onNavigationItemSelected")
fun setOnNavigationItemSelectedListener(
        view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("adapter")
fun bindAdapter(recyclerView: RecyclerView, adapter: GroupAdapter?) {
    recyclerView.adapter = adapter
}