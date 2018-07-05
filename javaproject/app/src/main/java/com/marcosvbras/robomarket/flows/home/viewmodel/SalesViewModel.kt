package com.marcosvbras.robomarket.flows.home.viewmodel

import android.databinding.ObservableBoolean
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.business.model.SaleModel
import com.marcosvbras.robomarket.flows.home.ui.adapter.SaleAdapter
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

class SalesViewModel(private val callback: BaseActivityCallback) : BaseViewModel(), OnRecyclerClick {

    private var disposable: Disposable? = null
    private val saleModel: SaleModel = SaleModel()
    private var skip = 0
    private var listSale = mutableListOf<Sale>()
    var saleAdapter: SaleAdapter? = SaleAdapter(this)
    var isListEmpty = ObservableBoolean(false)
    var isRefreshing = ObservableBoolean(false)

    init {
        listSales()
    }

    private fun listSales() {
        saleModel.listSales(App.getInstance().user.objectId!!, skip)
                ?.subscribe({ next ->
                    listSale = next?.results!!
                    saleAdapter?.updateItems(listSale)
                    isListEmpty.set(listSale.size == 0)
                }, { error ->
                    cleanupSubscriptions()
                }, this::cleanupSubscriptions, { d ->
                    isLoading.set(true)
                    cleanupSubscriptions()
                    disposable = d
                })
    }

    fun onRefresh() {
        cleanupSubscriptions()

        saleModel.listSales(App.getInstance().user.objectId!!, skip)
                ?.subscribe({ next ->
                    listSale = next.results!!
                    saleAdapter?.updateItems(listSale)
                    isListEmpty.set(listSale.isEmpty())
                }, { error ->
                    cleanupSubscriptions()
                }, {
                    cleanupSubscriptions()
                }, { d ->
                    isRefreshing.set(true)
                    disposable = d
                })
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        isRefreshing.set(false)
        disposable?.dispose()
    }

    override fun onClick(obj: Any) {

    }

    override fun onLongClick(obj: Any) {

    }
}