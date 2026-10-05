package dev.aleksejgolikov.espresso.actions

import android.os.SystemClock
import android.view.View
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

fun ViewInteraction.waitUntil(
    matcher: Matcher<View>,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): ViewInteraction {
    val deadline = SystemClock.elapsedRealtime() + timeoutMs
    var last: Throwable? = null
    while (SystemClock.elapsedRealtime() < deadline) {
        try {
            check(matches(matcher))
            return this
        } catch (error: Throwable) {
            last = error
            SystemClock.sleep(POLL_MS)
        }
    }
    throw last ?: AssertionError("ViewInteraction wait timed out after ${timeoutMs}ms")
}

fun ViewInteraction.waitDisplayed(timeoutMs: Long = DEFAULT_WAIT_MS): ViewInteraction =
    waitUntil(isDisplayed(), timeoutMs)

fun ViewInteraction.waitNotDisplayed(timeoutMs: Long = DEFAULT_WAIT_MS): ViewInteraction =
    waitUntil(not(isDisplayed()), timeoutMs)

fun ViewInteraction.waitGone(timeoutMs: Long = DEFAULT_WAIT_MS): ViewInteraction {
    val deadline = SystemClock.elapsedRealtime() + timeoutMs
    var last: Throwable? = null
    while (SystemClock.elapsedRealtime() < deadline) {
        try {
            check(doesNotExist())
            return this
        } catch (error: Throwable) {
            last = error
            SystemClock.sleep(POLL_MS)
        }
    }
    throw last ?: AssertionError("View still exists after ${timeoutMs}ms")
}

fun ViewInteraction.waitEnabled(
    enabled: Boolean = true,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): ViewInteraction =
    waitUntil(if (enabled) isEnabled() else not(isEnabled()), timeoutMs)

fun ViewInteraction.waitText(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): ViewInteraction = waitUntil(withText(text), timeoutMs)

fun ViewInteraction.tapWhenShown(timeoutMs: Long = DEFAULT_WAIT_MS): ViewInteraction =
    waitDisplayed(timeoutMs).perform(click())

fun ViewInteraction.typeWhenShown(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): ViewInteraction =
    waitDisplayed(timeoutMs).perform(clearText(), replaceText(text), closeSoftKeyboard())

fun ViewInteraction.scrollIntoView(): ViewInteraction = perform(scrollTo())

fun ViewInteraction.swipe(direction: SwipeDir): ViewInteraction =
    perform(
        when (direction) {
            SwipeDir.UP -> swipeUp()
            SwipeDir.DOWN -> swipeDown()
            SwipeDir.LEFT -> swipeLeft()
            SwipeDir.RIGHT -> swipeRight()
        },
    )

enum class SwipeDir { UP, DOWN, LEFT, RIGHT }
