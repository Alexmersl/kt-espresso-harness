package dev.aleksejgolikov.espresso.actions

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

fun uiDevice(): UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

fun waitForPackage(
    packageName: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
) {
    val shown = uiDevice().wait(Until.hasObject(By.pkg(packageName).depth(0)), timeoutMs)
    check(shown) { "Package $packageName did not appear in ${timeoutMs}ms" }
}

fun deviceBack() {
    uiDevice().pressBack()
}

fun deviceHome() {
    uiDevice().pressHome()
}
