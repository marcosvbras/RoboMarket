package com.marcosvbras.robomarket.flows.selectrobot.viewmodel

import android.app.Activity.RESULT_OK
import android.os.Bundle
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.flows.home.ui.adapter.RobotAdapter
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class SelectRobotViewModel(private val callback: BaseActivityCallback) : BaseViewModel(), OnRecyclerClick {

    private val robotModel: RobotModel = RobotModel()
    private var disposable: Disposable? = null
    private var skip = 0
    private var listRobots: List<Robot>? = ArrayList()
    val robotAdapter: RobotAdapter = RobotAdapter(this)

    init {
        listRobots(null)
    }

    fun listRobots(query: String?) {
        cleanupSubscriptions()

        robotModel.listRobots(App.getInstance().user.objectId!!, query, skip)
                ?.subscribe({ next ->
                    listRobots = next.results
                    robotAdapter.updateItems(listRobots)
                }, { error ->
                    cleanupSubscriptions()
                }, {
                    cleanupSubscriptions()
                }, { d ->
                    cleanupSubscriptions()
                    disposable = d
                })
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        disposable?.dispose()
    }

    override fun onClick(obj: Any) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.Other.ROBOT_TAG, obj as Robot)
        callback.setActivityResult(RESULT_OK, bundle)
        callback.finishCurrentActivity()
    }

    override fun onLongClick(obj: Any) {

    }
}