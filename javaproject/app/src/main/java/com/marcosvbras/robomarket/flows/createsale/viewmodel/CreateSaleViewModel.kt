package com.marcosvbras.robomarket.flows.createsale.viewmodel

import android.annotation.SuppressLint
import android.databinding.ObservableField
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.SELECT_ROBOT_REQUEST_CODE
import com.marcosvbras.robomarket.business.beans.*
import com.marcosvbras.robomarket.business.model.RobotModel
import com.marcosvbras.robomarket.business.model.SaleModel
import com.marcosvbras.robomarket.flows.createsale.ui.adapter.RobotSaleAdapter
import com.marcosvbras.robomarket.flows.dialog.DialogFormActions
import com.marcosvbras.robomarket.flows.dialog.SaleDialogFragment
import com.marcosvbras.robomarket.flows.selectrobot.ui.SelectRobotActivity
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class CreateSaleViewModel(private val callback: ActivityCallback) : BaseViewModel(), OnRecyclerClick, DialogFormActions {

    private var disposable: Disposable? = null
    private val listRobotQuantity = mutableListOf<ItemRobotQuantity>()
    private val saleModel: SaleModel = SaleModel()
    private val robotModel: RobotModel = RobotModel()
    val robotSaleAdapter: RobotSaleAdapter = RobotSaleAdapter(this)
    var totalPrice = ObservableField<String>()

    fun select() {
        callback.openActivityForResult(
                SelectRobotActivity::class.java, null, SELECT_ROBOT_REQUEST_CODE
        )
    }

    fun addRobot(robot: Robot?) {
        if (robot != null) {
            var isAlreadySelected = false

            for ((robot1) in listRobotQuantity) {
                if (robot1.objectId == robot.objectId) {
                    isAlreadySelected = true
                    break
                }
            }

            if (!isAlreadySelected) {
                listRobotQuantity.add(ItemRobotQuantity(robot, 1))
                robotSaleAdapter.updateItems(listRobotQuantity)
            }
        }

        updateFinalValue()
    }

    fun save() {
        if (listRobotQuantity.size > 0) {
            val sale = Sale()
            sale.userId = App.instance.user?.objectId
            val items = ArrayList<ItemRobotSale>()

            for ((robot, itemQuantity) in listRobotQuantity) {
                items.add(
                        ItemRobotSale(
                                itemQuantity,
                                robot.price,
                                robot.objectId
                        )
                )
            }

            sale.robots = RobotSale(items)
            saleModel.createSale(sale)!!.subscribe(
                    { (objectId, userId, robots, createdAt) ->

                    }, { error ->
                isLoading.set(false)
                callback.showDialogMessage(error.message!!)
                cleanupSubscriptions()
            }, {
                isLoading.set(false)
                cleanupSubscriptions()
                decreaseStock()
            }, { d ->
                isLoading.set(true)
                disposable = d
            })

        } else {
            callback.showDialogMessage(R.string.select_robots_to_continue)
        }
    }

    @SuppressLint("CheckResult")
    private fun decreaseStock() {
        Observable.fromArray<Any>(*listRobotQuantity.toTypedArray())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ next ->
                    val robot = Robot()
                    robot.quantity = (next as ItemRobotQuantity).robot.quantity?.minus(next.itemQuantity)

                    robotModel.updateRobot(next.robot.objectId!!, robot)!!
                            .subscribe({ n -> }, { e -> }, { })
                }, { error ->

                }, { callback.finishCurrentActivity() }, { d ->

                })
    }

    override fun onClick(obj: Any) {
        val saleDialogFragment = SaleDialogFragment.newInstance(obj as ItemRobotQuantity, this)
        callback.showCustomAlertDialog(saleDialogFragment)
    }

    override fun onSave(itemRobotQuantity: ItemRobotQuantity) {
        for (rs in listRobotQuantity) {
            if (rs.robot.objectId == itemRobotQuantity.robot.objectId) {
                rs.itemQuantity = itemRobotQuantity.itemQuantity
                break
            }
        }

        updateFinalValue()
    }

    override fun onLongClick(obj: Any) {

    }

    override fun onRemove(itemRobotQuantity: ItemRobotQuantity) {
        for (rs in listRobotQuantity) {
            if (rs.robot.objectId == itemRobotQuantity.robot.objectId) {
                listRobotQuantity.remove(rs)
                break
            }
        }

        robotSaleAdapter.updateItems(listRobotQuantity)
        updateFinalValue()
    }

    private fun updateFinalValue() {
        var total = 0

        for ((robot, itemQuantity) in listRobotQuantity)
            total += robot.price!! * itemQuantity

        totalPrice.set("$ $total")
    }

    override fun onCleared() {
        super.onCleared()
        cleanupSubscriptions()
    }

    override fun cleanupSubscriptions() {
        if (disposable != null && !disposable!!.isDisposed)
            disposable!!.dispose()
    }
}