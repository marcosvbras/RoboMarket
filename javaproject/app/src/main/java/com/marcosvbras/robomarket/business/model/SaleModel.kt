package com.marcosvbras.robomarket.business.model

import com.marcosvbras.robomarket.business.api.APIService
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.business.response.ListSaleResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleModel {

    fun listSales() : Observable<ListSaleResponse>? {
//        return APIService.getService().listSales()
        return null
    }

    fun getSale(objectId: String) : Observable<Sale>? {
        return APIService.getService().getSale(objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun createSale(sale: Sale) : Observable<Sale> {
        return APIService.getService().createSale(sale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}