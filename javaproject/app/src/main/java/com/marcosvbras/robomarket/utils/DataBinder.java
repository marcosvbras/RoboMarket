package com.marcosvbras.robomarket.utils;

import android.databinding.BindingAdapter;
import android.widget.EditText;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void setError(EditText editText, ErrorObservable errorObservable) {
        if(errorObservable.hasErrorSetted()) {
            if(errorObservable.getIntError() != null)
                editText.setError(editText.getContext().getString(errorObservable.getIntError()));
            else if(errorObservable.get() != null)
                editText.setError(errorObservable.get());

            errorObservable.clear();
        }
    }

//
//    @BindingAdapter({"adapter", "data"})
//    public static void bindData(RecyclerView recyclerView, EnterpriseAdapter adapter, List<Enterprise> list) {
//        recyclerView.setAdapter(adapter);
//        adapter.updateItems(list);
//    }
}
