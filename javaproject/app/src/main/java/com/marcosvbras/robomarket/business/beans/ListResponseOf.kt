package com.marcosvbras.robomarket.business.beans

data class ListResponseOf<T>(var results: MutableList<T>? = null)