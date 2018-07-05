package com.marcosvbras.robomarket.flows.home.ui.adapter

import android.view.View
import android.view.animation.AnimationUtils
import com.genius.groupie.GroupAdapter
import com.genius.groupie.ViewHolder
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick

class SaleAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    private var lastPosition = -1

    fun updateItems(listSale: MutableList<Sale>) {
        clear()
        listSale.forEach { add(ItemSale(it, onRecyclerClick)) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        setAnimation(position, holder.itemView)
    }

    private fun setAnimation(position: Int, viewToAnimate: View) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

}