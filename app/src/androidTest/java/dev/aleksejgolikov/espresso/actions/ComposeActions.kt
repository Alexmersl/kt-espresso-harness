package dev.aleksejgolikov.espresso.actions

import androidx.compose.ui.test.junit4.ComposeTestRule

fun ComposeTestRule.node(tag: String) = nodeByTag(tag)

fun ComposeTestRule.tapTag(tag: String) {
    waitUntilTagShown(tag).tapWhenShown()
}

fun ComposeTestRule.typeIntoTag(
    tag: String,
    text: String,
) {
    waitUntilTagShown(tag).typeWhenShown(text)
}

fun ComposeTestRule.mustShowTag(tag: String) {
    waitUntilTagShown(tag)
}

fun ComposeTestRule.mustHaveText(
    tag: String,
    text: String,
) {
    waitUntilTagShown(tag).waitText(text)
}
