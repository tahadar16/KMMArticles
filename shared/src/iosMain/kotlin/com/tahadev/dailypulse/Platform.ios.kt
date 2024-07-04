package com.tahadev.dailypulse

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

actual class Platform() {
    actual val osName: String
        get() = UIDevice.currentDevice.name
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model
    actual val screenDensity: Int
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logDeviceInfo() {
        NSLog(
            "osName-> $osName, \n osVersion-> $osVersion, \n deviceModel-> $deviceModel,\n screenDensity-> $screenDensity}"
        )
    }
}