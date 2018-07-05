package com.marcosvbras.robomarket.flows.createrobot.viewmodel

import android.app.Activity.RESULT_OK
import android.databinding.ObservableField
import android.os.Bundle
import android.text.TextUtils
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.*

class CreateRobotViewModel(private val callback: BaseActivityCallback, private var savedRobot: Robot?) : BaseViewModel() {

    private var disposable: Disposable? = null
    private val robotModel: RobotModel
    var model = ObservableField<String>()
    var color = ObservableField<String>()
    var year = ObservableField<String>()
    var price = ObservableField<String>()
    var manufacturer = ObservableField<String>()
    var quantity = ObservableField("0")
    var imageUrl = ObservableField<String>()
    var modelFieldError = ErrorObservable()
    var manufacturerFieldError = ErrorObservable()
    var priceFieldError = ErrorObservable()

    private val isFormValid: Boolean
        get() {
            if (TextUtils.isEmpty(model.get())) {
                modelFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(manufacturer.get())) {
                manufacturerFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(price.get())) {
                priceFieldError.errorResource = R.string.required_field
                return false
            }

            return true
        }

    init {
        checkAction()
        robotModel = RobotModel()
    }

    private fun checkAction() {
        if (savedRobot != null) {
            model.set(savedRobot!!.model)
            color.set(savedRobot!!.color)
            year.set(savedRobot!!.year.toString())
            price.set(savedRobot!!.price.toString())
            manufacturer.set(savedRobot!!.manufacturer)
            quantity.set(savedRobot!!.quantity.toString())
            imageUrl.set(savedRobot!!.imageUrl)
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

    fun save() {
        if (isFormValid) {
            cleanupSubscriptions()

            val robot = Robot()
            robot.color = color.get()
            robot.imageUrl = imageUrl.get()
            robot.manufacturer = manufacturer.get()
            robot.price = if (TextUtils.isEmpty(price.get())) 0 else Integer.parseInt(price.get()!!)
            robot.quantity = if (TextUtils.isEmpty(quantity.get())) 0 else Integer.parseInt(quantity.get()!!)
            robot.model = model.get()

            if (!TextUtils.isEmpty(year.get()))
                robot.year = Integer.parseInt(year.get()!!)

            if (savedRobot == null) {
                robot.userId = App.getInstance().user.objectId
                robotModel.createRobot(robot)!!
                        .subscribe({ next ->

                        }, { error ->
                            isLoading.set(false)
                            callback.showDialogMessage(error.message!!)
                        }, {
                            isLoading.set(false)
                            callback.setActivityResult(RESULT_OK)
                            callback.finishCurrentActivity()
                        }, { d ->
                            isLoading.set(true)
                            disposable = d
                        })
            } else {
                robotModel.updateRobot(savedRobot!!.objectId!!, robot)!!
                        .subscribe({ next -> this.savedRobot = next }, { error ->
                            isLoading.set(false)
                            callback.showDialogMessage(error.message!!)
                        }, {
                            isLoading.set(false)
                            val bundle = Bundle()
                            bundle.putParcelable(Constants.Other.ROBOT_TAG, savedRobot)
                            callback.setActivityResult(RESULT_OK, bundle)
                            callback.finishCurrentActivity()
                        }, { d ->
                            isLoading.set(true)
                            disposable = d
                        })
            }
        }
    }

    fun changeAvatarImg() {
        imageUrl.set(Constants.Other.ROBOHASH_API + Random().nextInt(500) + Constants.Other.ROBOHASH_SET_1_PARAM)
    }

    fun increaseQuantity() {
        if (TextUtils.isEmpty(quantity.get())) {
            quantity.set("1")
            return
        }

        val quantityInt = Integer.parseInt(quantity.get()!!) + 1
        quantity.set(quantityInt.toString())
    }

    fun decreaseQuantity() {
        if (TextUtils.isEmpty(quantity.get())) {
            quantity.set("0")
            return
        }

        var quantityInt = Integer.parseInt(quantity.get()!!)

        if (quantityInt > 0)
            quantityInt--

        quantity.set(quantityInt.toString())
    }
}