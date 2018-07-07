package com.marcosvbras.robomarket.flows.home.ui.adapter

import android.view.View
import com.genius.groupie.Item
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.databinding.ItemSaleBinding
import com.marcosvbras.robomarket.flows.home.viewmodel.ItemSaleViewModel
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class ItemSale(private val sale: Sale, private val onRecyclerClick: OnRecyclerClick) : Item<ItemSaleBinding>(), View.OnClickListener {
    private val viewModel: ItemSaleViewModel

    init {
        viewModel = ItemSaleViewModel(sale)
    }

    override fun getLayout(): Int {
        return R.layout.item_sale
    }

    override fun bind(viewBinding: ItemSaleBinding, position: Int) {
        viewBinding.viewModel = viewModel
        viewBinding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        onRecyclerClick.onClick(sale)
    }
}