package dev.aleksejgolikov.espresso.framework.nativeui

import android.os.SystemClock
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import dev.aleksejgolikov.espresso.actions.DEFAULT_WAIT_MS
import dev.aleksejgolikov.espresso.actions.POLL_MS
import dev.aleksejgolikov.espresso.actions.uiDevice

fun NativeElement.find(timeoutMs: Long = DEFAULT_WAIT_MS): UiObject2 =
    uiDevice().wait(Until.findObject(selector), timeoutMs)
        ?: error("Native element not found within ${timeoutMs}ms: $selector")

fun NativeElement.exists(timeoutMs: Long = DEFAULT_WAIT_MS): Boolean =
    uiDevice().wait(Until.hasObject(selector), timeoutMs)

fun NativeElement.waitShown(timeoutMs: Long = DEFAULT_WAIT_MS): UiObject2 = find(timeoutMs)

fun NativeElement.tap(timeoutMs: Long = DEFAULT_WAIT_MS): UiObject2 =
    find(timeoutMs).also { it.click() }

fun NativeElement.longTap(timeoutMs: Long = DEFAULT_WAIT_MS): UiObject2 =
    find(timeoutMs).also { it.longClick() }

fun NativeElement.type(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): UiObject2 =
    find(timeoutMs).also { node ->
        node.click()
        node.text = text
    }

fun NativeElement.clear(timeoutMs: Long = DEFAULT_WAIT_MS): UiObject2 =
    find(timeoutMs).also { it.clear() }

fun NativeElement.swipe(
    direction: Direction,
    percent: Float = 0.6f,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): UiObject2 = find(timeoutMs).also { it.swipe(direction, percent) }

fun NativeElement.scrollUntilFound(
    timeoutMs: Long = DEFAULT_WAIT_MS,
    direction: Direction = Direction.DOWN,
): UiObject2 {
    val list =
        uiDevice().findObject(androidx.test.uiautomator.By.scrollable(true))
            ?: error("No scrollable container on screen")
    val deadline = SystemClock.elapsedRealtime() + timeoutMs
    while (SystemClock.elapsedRealtime() < deadline) {
        val found = uiDevice().findObject(selector)
        if (found != null) return found
        list.scroll(direction, 0.7f)
        SystemClock.sleep(POLL_MS)
    }
    return find(1)
}
