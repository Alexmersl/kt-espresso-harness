package dev.aleksejgolikov.espresso.framework.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import dev.aleksejgolikov.espresso.actions.DEFAULT_WAIT_MS

fun ComposeElement.node() = rule.onNode(matcher, useUnmergedTree = true)

fun ComposeElement.waitShown(timeoutMs: Long = DEFAULT_WAIT_MS): ComposeElement {
    rule.waitUntil(timeoutMs) {
        rule.onAllNodes(matcher, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    }
    node().assertIsDisplayed()
    return this
}

fun ComposeElement.waitGone(timeoutMs: Long = DEFAULT_WAIT_MS): ComposeElement {
    rule.waitUntil(timeoutMs) {
        rule.onAllNodes(matcher, useUnmergedTree = true).fetchSemanticsNodes().isEmpty()
    }
    return this
}

fun ComposeElement.tap(timeoutMs: Long = DEFAULT_WAIT_MS): ComposeElement {
    waitShown(timeoutMs)
    node().performClick()
    return this
}

fun ComposeElement.type(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): ComposeElement {
    waitShown(timeoutMs)
    node().performTextClearance()
    node().performTextInput(text)
    return this
}

fun ComposeElement.scrollUntilVisible(): ComposeElement {
    waitShown()
    node().performScrollTo()
    return this
}

fun ComposeElement.scrollToTag(itemTag: String): ComposeElement {
    waitShown()
    node().performScrollToNode(hasTestTag(itemTag))
    return this
}

fun ComposeElement.scrollToText(
    text: String,
    substring: Boolean = false,
): ComposeElement {
    waitShown()
    node().performScrollToNode(hasText(text, substring = substring))
    return this
}

fun ComposeElement.scrollToIndex(index: Int): ComposeElement {
    waitShown()
    node().performScrollToIndex(index)
    return this
}

fun ComposeElement.scrollToKey(key: Any): ComposeElement {
    waitShown()
    node().performScrollToKey(key)
    return this
}
