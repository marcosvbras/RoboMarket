package com.marcosvbras.robomarket.flows.home.ui.adapter

import com.genius.groupie.GroupAdapter
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class SaleAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    fun updateItems(listSale: MutableList<Sale>) {
        clear()
        listSale.forEach { add(ItemSale(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }

}