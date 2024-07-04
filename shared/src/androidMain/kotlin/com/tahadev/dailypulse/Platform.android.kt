package com.tahadev.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

actual class Platform() {

    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val screenDensity: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logDeviceInfo() {
        Log.d(
            TAG,
            "osName-> $osName, \n osVersion-> $osVersion, \n deviceModel-> $deviceModel,\n screenDensity-> $screenDensity}"
        )
    }

    companion object {
        const val TAG = "DailyPulse Platform"
    }
}