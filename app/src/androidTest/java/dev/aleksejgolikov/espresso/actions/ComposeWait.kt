package dev.aleksejgolikov.espresso.actions

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput

fun ComposeTestRule.waitUntilNode(
    timeoutMs: Long = DEFAULT_WAIT_MS,
    present: () -> Boolean,
) {
    waitUntil(timeoutMs, present)
}

fun ComposeTestRule.waitUntilTagShown(
    tag: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
): SemanticsNodeInteraction {
    waitUntil(timeoutMs) {
        onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    }
    return onNodeWithTag(tag, useUnmergedTree = true).assertIsDisplayed()
}

fun ComposeTestRule.waitUntilTagGone(
    tag: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
) {
    waitUntil(timeoutMs) {
        onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isEmpty()
    }
}

fun ComposeTestRule.waitUntilTextShown(
    text: String,
    timeoutMs: Long = DEFAULT_WAIT_MS,
    substring: Boolean = false,
): SemanticsNodeInteraction {
    waitUntil(timeoutMs) {
        onAllNodesWithText(text, substring = substring, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    }
    return onNodeWithText(text, substring = substring, useUnmergedTree = true).assertIsDisplayed()
}

fun SemanticsNodeInteraction.waitDisplayed(): SemanticsNodeInteraction = assertIsDisplayed()

fun SemanticsNodeInteraction.waitNotDisplayed(): SemanticsNodeInteraction = assertIsNotDisplayed()

fun SemanticsNodeInteraction.waitEnabled(enabled: Boolean = true): SemanticsNodeInteraction =
    if (enabled) assertIsEnabled() else assertIsNotEnabled()

fun SemanticsNodeInteraction.tapWhenShown(): SemanticsNodeInteraction =
    assertIsDisplayed().performClick()

fun SemanticsNodeInteraction.typeWhenShown(text: String): SemanticsNodeInteraction {
    assertIsDisplayed()
    performTextClearance()
    performTextInput(text)
    return this
}

fun SemanticsNodeInteraction.waitText(text: String): SemanticsNodeInteraction =
    assertIsDisplayed().assertTextEquals(text)
