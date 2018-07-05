package com.marcosvbras.robomarket.flows.home.viewmodel

import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.flows.home.ui.adapter.RobotAdapter
import com.marcosvbras.robomarket.flows.robotdetail.ui.RobotDetailActivity
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class RobotsViewModel(private val callback: BaseActivityCallback) : BaseViewModel(), OnRecyclerClick {

    private val robotModel: RobotModel = RobotModel()
    private var disposable: Disposable? = null
    private val skip = 0
    private var listRobots: List<Robot>? = ArrayList()
    val robotAdapter: RobotAdapter = RobotAdapter(this)
    var isListEmpty = ObservableBoolean(false)
    var isLoadingMore = ObservableBoolean(false)

    val scrollListener : RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!isLoadingMore.get() && robotAdapter.itemCount <= lastVisibleItem) {
//                        if (onLoadMoreListener != null) {
//                            onLoadMoreListener.onLoadMore()
//                        }
                        isLoadingMore.set(true)
                    }
                }
            }

    init {
        listRobots(null)
    }

    fun listRobots(query: String?) {
        cleanupSubscriptions()

        robotModel.listRobots(App.getInstance().user.objectId!!, query, skip)
                ?.subscribe({ next ->
                    // listRobots.addAll(next.getListRobots());
                    listRobots = next.results
                    robotAdapter.updateItems(listRobots)
                    isListEmpty.set(listRobots!!.isEmpty())
                }, { error -> cleanupSubscriptions() }, { this.cleanupSubscriptions() }, { d ->
                    isLoading.set(true)
                    disposable = d
                })
    }

    fun onRefresh() {
        listRobots(null)
    }

    override fun onCleared() {
        super.onCleared()
        cleanupSubscriptions()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        disposable?.dispose()
    }

    override fun onClick(obj: Any) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.Other.ROBOT_TAG, obj as Robot)
        callback.openActivity(RobotDetailActivity::class.java, bundle, false)
    }

    override fun onLongClick(obj: Any) {

    }
}