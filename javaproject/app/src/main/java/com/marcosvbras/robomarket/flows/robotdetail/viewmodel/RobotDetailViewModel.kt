package com.marcosvbras.robomarket.flows.robotdetail.viewmodel

import android.app.Activity.RESULT_OK
import android.databinding.ObservableField
import android.os.Bundle
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.flows.createrobot.ui.CreateRobotActivity
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

class RobotDetailViewModel(private val callback: ActivityCallback) : BaseViewModel() {

    private var robot: Robot? = null
    private val robotModel: RobotModel = RobotModel()
    private var disposable: Disposable? = null
    var model = ObservableField<String>()
    var manufacturer = ObservableField<String>()
    var color = ObservableField<String>()
    var year = ObservableField<String>()
    var quantity = ObservableField<String>()
    var price = ObservableField<String>()
    var imageUrl = ObservableField<String>()

    fun showData(robot: Robot?) {
        this.robot = robot

        if (robot != null) {
            model.set(robot.model)
            manufacturer.set(robot.manufacturer)
            color.set(robot.color)
            year.set(robot.year.toString())
            quantity.set(robot.quantity.toString())
            price.set(robot.price.toString())
            imageUrl.set(robot.imageUrl.toString())
        }
    }

    fun edit() {
        val bundle = Bundle()
        bundle.putParcelable(Constants.Other.ROBOT_TAG, robot)

        callback.openActivityForResult(
                CreateRobotActivity::class.java, bundle, Constants.Other.EDIT_ROBOT_REQUEST_CODE
        )
    }

    fun delete() {
        cleanupSubscriptions()

        if (robot != null) {
            robotModel.deleteRobot(robot!!.objectId!!)!!
                    .subscribe({

                    }, { error ->
                        cleanupSubscriptions()
                        callback.showDialogMessage(error.message!!)
                    }, {
                        cleanupSubscriptions()
                        callback.setActivityResult(RESULT_OK)
                        callback.finishCurrentActivity()
                    }, { d ->
                        isLoading.set(true)
                        disposable = d
                    })
        }
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        disposable?.dispose()
    }
}