package com.marcosvbras.robomarket.flows.home.viewmodel

import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.api.APIService.Companion.DEFAULT_ITEM_PAGINATION
import com.marcosvbras.robomarket.business.api.APIService.Companion.DEFAULT_ITEM_SKIP
import com.marcosvbras.robomarket.business.model.SaleModel
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.home.ui.adapter.SaleAdapter
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.disposables.Disposable

class SalesViewModel(private val callback: HomeActivityCallback) : BaseViewModel(), OnRecyclerClick {

    private var disposable: Disposable? = null
    private val saleModel: SaleModel = SaleModel()
    private var skip = 0
    private var lastItemCountResponse = 0
    var saleAdapter: SaleAdapter = SaleAdapter(this)
    var isListEmpty = ObservableBoolean(false)
    var isLoadingMore = ObservableBoolean(false)

    init {
        listSales()
    }

    val scrollListener : RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1

                    if (!isLoadingMore.get() && saleAdapter.itemCount == lastVisibleItem &&
                            lastItemCountResponse == DEFAULT_ITEM_PAGINATION) {
                        skip += DEFAULT_ITEM_SKIP
                        loadMoreSales()
                    }
                }
            }

    private fun listSales() {
        skip = 0
        cleanupSubscriptions()

        saleModel.listSales(App.instance.user?.objectId!!, skip)
                ?.subscribe({ next ->
                    lastItemCountResponse = next.results?.size ?: 0
                    saleAdapter.updateItems(next.results?: mutableListOf())
                    isListEmpty.set(saleAdapter.itemCount == 0)
                }, { error ->
                    cleanupSubscriptions()
                }, this::cleanupSubscriptions, { d ->
                    isLoading.set(true)
                    disposable = d
                })
    }

    fun loadMoreSales() {
        cleanupSubscriptions()

        saleModel.listSales(App.instance.user?.objectId!!, skip)
                ?.subscribe({ next ->
                    lastItemCountResponse = next.results?.size ?: 0
                    saleAdapter.updateItems(next.results?: mutableListOf(), true)
                }, { error ->
                    callback.showDialogMessage(error.message!!)
                    cleanupSubscriptions()
                }, { this.cleanupSubscriptions() }, { d ->
                    isLoadingMore.set(true)
                    disposable = d
                })
    }

    fun onRefresh() {
        listSales()
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        isLoadingMore.set(false)
        disposable?.dispose()
    }

    override fun onClick(obj: Any) {

    }

    override fun onLongClick(obj: Any) {

    }
}