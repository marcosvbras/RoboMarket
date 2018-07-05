package com.marcosvbras.robomarket.flows.home.ui.adapter

import android.view.View
import com.genius.groupie.GroupAdapter
import com.genius.groupie.ViewHolder
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import java.util.ArrayList
import android.view.animation.AnimationUtils

class RobotAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    private var listRobot: List<Robot>? = null
    private var lastPosition = -1

    fun updateItems(listItems: List<Robot>?) {
        clear()
        listRobot = listItems ?: ArrayList()
        listItems?.forEach { add(ItemRobot(it, onRecyclerClick)) }
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

    override fun onViewDetachedFromWindow(holder: ViewHolder<*>) {
        holder.itemView.clearAnimation()
        super.onViewDetachedFromWindow(holder)
    }
}