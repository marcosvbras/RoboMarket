package com.marcosvbras.robomarket.business.domain

import com.marcosvbras.robomarket.business.domain.RobotSale

data class Sale(var objectId: String? = null, var userId: String? = null,
        var robots: RobotSale? = null, var createdAt: String? = null)