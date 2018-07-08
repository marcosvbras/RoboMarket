package com.marcosvbras.robomarket.business.model

import com.marcosvbras.robomarket.business.api.APIService
import com.marcosvbras.robomarket.business.beans.ListResponseOf
import com.marcosvbras.robomarket.business.beans.Sale
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleModel {

    private val whereClause = "{\"userId\": \"{userId}\"}"

    fun listSales(userId: String, skip: Int) : Observable<ListResponseOf<Sale>>? {
        return APIService.getService()?.listSales(
                whereClause.replace("{userId}", userId),
                APIService.DEFAULT_ROBOT_ORDER,
                APIService.DEFAULT_ITEM_PAGINATION,
                skip
        )?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getSale(objectId: String) = APIService.getService()?.getSale(objectId)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())

    fun createSale(sale: Sale) : Observable<Sale>? {
        return APIService.getService()?.createSale(sale)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

}