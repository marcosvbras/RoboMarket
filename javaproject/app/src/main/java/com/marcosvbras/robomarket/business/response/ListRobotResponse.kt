package com.marcosvbras.robomarket.business.response

import com.marcosvbras.robomarket.business.domain.Robot

data class ListRobotResponse(var results: MutableList<Robot>? = null)