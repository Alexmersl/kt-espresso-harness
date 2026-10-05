package dev.aleksejgolikov.espresso.framework.adb

import dev.aleksejgolikov.espresso.actions.uiDevice

/**
 * Shell via UI Automator (runs as `shell` on the device/emulator).
 * Wifi/data/airplane work on Google AVD images; OEM devices may ignore `svc`.
 */
object Adb {
    fun exec(command: String): String = uiDevice().executeShellCommand(command).trim()

    fun serial(): String = exec("getprop ro.serialno")

    fun setWifi(enabled: Boolean) {
        exec("svc wifi ${onOff(enabled)}")
    }

    fun setMobileData(enabled: Boolean) {
        exec("svc data ${onOff(enabled)}")
    }

    fun setAirplaneMode(enabled: Boolean) {
        exec("settings put global airplane_mode_on ${if (enabled) 1 else 0}")
        exec("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state $enabled")
    }

    fun disableInternet() {
        setWifi(false)
        setMobileData(false)
    }

    fun enableInternet() {
        setAirplaneMode(false)
        setWifi(true)
        setMobileData(true)
    }

    fun isAirplaneOn(): Boolean = exec("settings get global airplane_mode_on") == "1"

    private fun onOff(enabled: Boolean) = if (enabled) "enable" else "disable"
}
