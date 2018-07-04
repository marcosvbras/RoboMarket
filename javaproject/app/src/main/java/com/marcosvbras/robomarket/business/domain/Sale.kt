package com.marcosvbras.robomarket.business.domain

data class Sale(var objectId: String? = null, var userId: String? = null,
        var robots: RobotSale? = null, var createdAt: String? = null) {

    fun getRobotCount() = robots?.listItemRobots?.size

    fun getItemCount() : Int {
        var count = 0

        robots?.listItemRobots?.forEach {
            count += it.quantity!!
        }

        return count
    }

    fun getTotalPrice() : Int {
        var total = 0

        robots?.listItemRobots?.forEach {
            total += it.quantity!! * it.quantity!!
        }

        return total
    }
}