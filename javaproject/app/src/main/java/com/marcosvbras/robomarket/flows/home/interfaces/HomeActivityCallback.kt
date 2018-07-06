package com.marcosvbras.robomarket.flows.home.interfaces

import android.support.v4.app.Fragment
import com.marcosvbras.robomarket.interfaces.ActivityCallback

interface HomeActivityCallback : ActivityCallback {

    fun replaceFragment(fragment: Fragment, tag: String)

}