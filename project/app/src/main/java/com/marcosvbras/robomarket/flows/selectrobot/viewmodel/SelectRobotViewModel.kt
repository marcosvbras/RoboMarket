package com.marcosvbras.robomarket.flows.selectrobot.viewmodel

import android.app.Activity.RESULT_OK
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.ROBOT_TAG
import com.marcosvbras.robomarket.business.api.APIService.Companion.DEFAULT_ITEM_PAGINATION
import com.marcosvbras.robomarket.business.api.APIService.Companion.DEFAULT_ITEM_SKIP
import com.marcosvbras.robomarket.business.beans.Robot
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.flows.home.ui.adapter.RobotAdapter
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.disposables.Disposable

class SelectRobotViewModel(private val callback: ActivityCallback) : BaseViewModel(), OnRecyclerClick {

    private val robotModel: RobotModel = RobotModel()
    private var disposable: Disposable? = null
    private var skip = 0
    private var query: String? = null
    private var lastItemCountResponse = 0
    val robotAdapter: RobotAdapter = RobotAdapter(this)
    var isListEmpty = ObservableBoolean(false)
    var isLoadingMore = ObservableBoolean(false)

    init {
        listRobots(null)
    }

    val scrollListener : RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1

                    if (!isLoadingMore.get() && robotAdapter.itemCount == lastVisibleItem &&
                            lastItemCountResponse == DEFAULT_ITEM_PAGINATION) {
                        skip += DEFAULT_ITEM_SKIP
                        loadMoreRobots()
                    }
                }
            }

    fun listRobots(query: String?) {
        this.query = query
        skip = 0
        cleanupSubscriptions()

        robotModel.listRobots(App.instance.user?.objectId!!, query, skip)
                ?.subscribe({ next ->
                    lastItemCountResponse = next.results?.size ?: 0
                    robotAdapter.updateItems(next.results?: mutableListOf())
                    isListEmpty.set(robotAdapter.itemCount == 0)
                }, { error ->
                    callback.showDialogMessage(error.message!!)
                    cleanupSubscriptions()
                }, { this.cleanupSubscriptions() }, { d ->
                    isLoading.set(true)
                    disposable = d
                })
    }

    fun loadMoreRobots() {
        cleanupSubscriptions()

        robotModel.listRobots(App.instance.user?.objectId!!, query, skip)
                ?.subscribe({ next ->
                    lastItemCountResponse = next.results?.size ?: 0
                    robotAdapter.updateItems(next.results?: mutableListOf(), true)
                }, { error ->
                    callback.showDialogMessage(error.message!!)
                    cleanupSubscriptions()
                }, { this.cleanupSubscriptions() }, { d ->
                    isLoadingMore.set(true)
                    disposable = d
                })
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
        val bundle = Bundle()
        bundle.putParcelable(ROBOT_TAG, obj as Robot)
        callback.setActivityResult(RESULT_OK, bundle)
        callback.finishCurrentActivity()
    }

    override fun onLongClick(obj: Any) {}
}