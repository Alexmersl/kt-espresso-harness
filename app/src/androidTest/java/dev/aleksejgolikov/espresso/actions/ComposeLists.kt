package dev.aleksejgolikov.espresso.actions

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.SemanticsNodeInteraction

fun SemanticsNodeInteraction.scrollUntilVisible(): SemanticsNodeInteraction = performScrollTo()

fun ComposeTestRule.scrollListToTag(
    listTag: String,
    itemTag: String,
): SemanticsNodeInteraction =
    onNodeWithTag(listTag, useUnmergedTree = true).performScrollToNode(hasTestTag(itemTag))

fun ComposeTestRule.scrollListToText(
    listTag: String,
    text: String,
    substring: Boolean = false,
): SemanticsNodeInteraction =
    onNodeWithTag(listTag, useUnmergedTree = true)
        .performScrollToNode(hasText(text, substring = substring))

fun ComposeTestRule.scrollListToIndex(
    listTag: String,
    index: Int,
): SemanticsNodeInteraction =
    onNodeWithTag(listTag, useUnmergedTree = true).performScrollToIndex(index)

fun ComposeTestRule.scrollListToKey(
    listTag: String,
    key: Any,
): SemanticsNodeInteraction =
    onNodeWithTag(listTag, useUnmergedTree = true).performScrollToKey(key)

fun ComposeTestRule.tapListItemByText(
    listTag: String,
    text: String,
) {
    scrollListToText(listTag, text)
    waitUntilTextShown(text).tapWhenShown()
}

fun ComposeTestRule.tapListItemByTag(
    listTag: String,
    itemTag: String,
) {
    scrollListToTag(listTag, itemTag)
    waitUntilTagShown(itemTag).tapWhenShown()
}
