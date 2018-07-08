package com.marcosvbras.robomarket.business.beans

data class Sale(var objectId: String? = null, var userId: String? = null,
        var robots: RobotSale? = null, var createdAt: String? = null) {

    val robotCount
        get() = robots?.listItemRobots?.size

    val itemCount: Int
        get() {
            var count = 0

            robots?.listItemRobots?.forEach {
                count += it.quantity!!
            }

            return count
        }

    val totalPrice: Int
        get() {
            var total = 0

            robots?.listItemRobots?.forEach {
                total += it.quantity!! * it.quantity!!
            }

            return total
        }
}