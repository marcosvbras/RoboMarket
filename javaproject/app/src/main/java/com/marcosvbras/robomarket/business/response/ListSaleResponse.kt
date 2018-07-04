package com.marcosvbras.robomarket.business.response

import com.marcosvbras.robomarket.business.domain.Sale

data class ListSaleResponse(var results: MutableList<Sale>? = null)