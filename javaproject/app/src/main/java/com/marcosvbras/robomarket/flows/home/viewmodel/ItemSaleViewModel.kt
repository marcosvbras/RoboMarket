package com.marcosvbras.robomarket.flows.home.viewmodel

import android.annotation.SuppressLint
import android.support.v4.os.ConfigurationCompat
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.Sale
import java.text.NumberFormat
import java.text.SimpleDateFormat

class ItemSaleViewModel(private val sale: Sale) {

    val date: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
            val dateF = SimpleDateFormat("dd/MM/yyyy")
            return dateF.format(dateFormat.parse(sale.createdAt))
        }

    val total: String
        get() {
            val locale = ConfigurationCompat.getLocales(App.getInstance().resources.configuration)[0]
            return NumberFormat.getNumberInstance(locale).format(sale.getTotalPrice()).toString()
        }

    val itemCount: String
        get() = sale.getItemCount().toString()

}