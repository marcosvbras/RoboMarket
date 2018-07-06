package com.marcosvbras.robomarket.flows.home.ui.adapter

import android.view.View
import com.genius.groupie.GroupAdapter
import com.genius.groupie.ViewHolder
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import android.view.animation.AnimationUtils

class RobotAdapter(private val onRecyclerClick: OnRecyclerClick) : GroupAdapter() {

    var lastLoadedPosition = -1

    fun updateItems(listItems: List<Robot>, justAddValues: Boolean = false) {
        if (justAddValues) {
            val lastIndex = itemCount

            for((index, robot) in listItems.withIndex()) {
                add(ItemRobot(robot, onRecyclerClick))
                notifyItemInserted(lastIndex + index)
            }
        } else {
            clear()
            listItems.forEach { add(ItemRobot(it, onRecyclerClick)) }
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        setAnimation(position, holder.itemView)
    }

    private fun setAnimation(position: Int, viewToAnimate: View) {
        if (position > lastLoadedPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastLoadedPosition = position
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder<*>) {
        holder.itemView.clearAnimation()
        super.onViewDetachedFromWindow(holder)
    }
}