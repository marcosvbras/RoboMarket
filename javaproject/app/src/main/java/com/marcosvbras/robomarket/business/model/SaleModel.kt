package com.marcosvbras.robomarket.business.model

import com.marcosvbras.robomarket.business.api.APIService
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.business.response.ListSaleResponse
import com.marcosvbras.robomarket.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleModel {

    private val whereClause = "{\"userId\": \"{userId}\"}"

    fun listSales(userId: String, skip: Int) : Observable<ListSaleResponse>? {
        return APIService.getService()?.listSales(
                whereClause.replace("{userId}", userId),
                Constants.Api.DEFAULT_ROBOT_ORDER,
                Constants.Api.DEFAULT_ITEM_PAGINATION,
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