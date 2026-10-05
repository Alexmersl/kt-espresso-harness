package dev.aleksejgolikov.espresso.framework.view

import dev.aleksejgolikov.espresso.actions.DEFAULT_WAIT_MS
import dev.aleksejgolikov.espresso.actions.SwipeDir
import dev.aleksejgolikov.espresso.actions.scrollIntoView
import dev.aleksejgolikov.espresso.actions.scrollToItemWithText
import dev.aleksejgolikov.espresso.actions.swipe
import dev.aleksejgolikov.espresso.actions.tapWhenShown
import dev.aleksejgolikov.espresso.actions.typeWhenShown
import dev.aleksejgolikov.espresso.actions.waitDisplayed
import dev.aleksejgolikov.espresso.actions.waitEnabled
import dev.aleksejgolikov.espresso.actions.waitGone
import dev.aleksejgolikov.espresso.actions.waitText

fun EspressoElement.waitShown(timeoutMs: Long = DEFAULT_WAIT_MS) =
    apply { interaction().waitDisplayed(timeoutMs) }

fun EspressoElement.waitHidden(timeoutMs: Long = DEFAULT_WAIT_MS) =
    apply { interaction().waitGone(timeoutMs) }

fun EspressoElement.waitEnabled(
    enabled: Boolean = true,
    timeoutMs: Long = DEFAULT_WAIT_MS,
) = apply { interaction().waitEnabled(enabled, timeoutMs) }

fun EspressoElement.waitHasText(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
) = apply { interaction().waitText(text, timeoutMs) }

fun EspressoElement.tap(timeoutMs: Long = DEFAULT_WAIT_MS) =
    apply { interaction().tapWhenShown(timeoutMs) }

fun EspressoElement.type(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
) = apply { interaction().typeWhenShown(text, timeoutMs) }

fun EspressoElement.scrollTo() = apply { interaction().scrollIntoView() }

fun EspressoElement.scrollToItemWithText(text: String) =
    apply { interaction().waitDisplayed().scrollToItemWithText(text) }

fun EspressoElement.swipe(direction: SwipeDir) = apply { interaction().swipe(direction) }
