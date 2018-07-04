package com.marcosvbras.robomarket.flows.home.ui.adapter;

import android.view.View;

import com.genius.groupie.Item;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.Sale;
import com.marcosvbras.robomarket.databinding.ItemSaleBinding;
import com.marcosvbras.robomarket.flows.home.viewmodel.ItemSaleViewModel;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;


public class ItemSale extends Item<ItemSaleBinding> implements View.OnClickListener {

    private Sale sale;
    private OnRecyclerClick onRecyclerClick;
    private ItemSaleViewModel viewModel;

    public ItemSale(Sale sale, OnRecyclerClick onRecyclerClick) {
        this.sale = sale;
        this.onRecyclerClick = onRecyclerClick;
        viewModel = new ItemSaleViewModel(sale);
    }

    @Override
    public int getLayout() {
        return R.layout.item_sale;
    }

    @Override
    public void bind(ItemSaleBinding viewBinding, int position) {
        viewBinding.setViewModel(viewModel);
        viewBinding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onRecyclerClick.onClick(sale);
    }
}
