package dev.aleksejgolikov.espresso.actions

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onSibling
import androidx.compose.ui.test.onSiblings

fun ComposeTestRule.nodeByTag(tag: String): SemanticsNodeInteraction =
    onNodeWithTag(tag, useUnmergedTree = true)

fun ComposeTestRule.nodeByText(
    text: String,
    substring: Boolean = false,
): SemanticsNodeInteraction = onNodeWithText(text, substring = substring, useUnmergedTree = true)

fun ComposeTestRule.nodeByDesc(description: String): SemanticsNodeInteraction =
    onNodeWithContentDescription(description, useUnmergedTree = true)

fun SemanticsNodeInteraction.childAt(index: Int): SemanticsNodeInteraction = onChildAt(index)

fun SemanticsNodeInteraction.children() = onChildren()

fun SemanticsNodeInteraction.parent(): SemanticsNodeInteraction = onParent()

fun SemanticsNodeInteraction.siblings() = onSiblings()

fun SemanticsNodeInteraction.firstSibling(): SemanticsNodeInteraction = onSibling()

fun ComposeTestRule.descendantOfTag(
    parentTag: String,
    childTag: String,
): SemanticsNodeInteraction =
    onNode(hasTestTag(childTag).and(hasAnyAncestor(hasTestTag(parentTag))), useUnmergedTree = true)

fun ComposeTestRule.childOfTag(
    parentTag: String,
    childTag: String,
): SemanticsNodeInteraction =
    onNode(hasTestTag(childTag).and(hasParent(hasTestTag(parentTag))), useUnmergedTree = true)

fun ComposeTestRule.nodeWithTextUnderTag(
    parentTag: String,
    text: String,
    substring: Boolean = false,
): SemanticsNodeInteraction =
    onNode(
        hasText(text, substring = substring).and(hasAnyAncestor(hasTestTag(parentTag))),
        useUnmergedTree = true,
    )

fun ComposeTestRule.siblingOfTag(
    siblingTag: String,
    targetTag: String,
): SemanticsNodeInteraction =
    onNode(hasTestTag(targetTag).and(hasAnySibling(hasTestTag(siblingTag))), useUnmergedTree = true)

fun ComposeTestRule.parentOfTag(childTag: String): SemanticsNodeInteraction =
    nodeByTag(childTag).parent()

fun ComposeTestRule.nodeWithDescendantText(
    parentTag: String,
    text: String,
): SemanticsNodeInteraction =
    onNode(hasTestTag(parentTag).and(hasAnyDescendant(hasText(text))), useUnmergedTree = true)

fun ComposeTestRule.nodeWithChildTag(
    parentTag: String,
    childTag: String,
): SemanticsNodeInteraction =
    onNode(hasTestTag(parentTag).and(hasAnyChild(hasTestTag(childTag))), useUnmergedTree = true)

fun ComposeTestRule.childMatching(
    parentTag: String,
    matcher: androidx.compose.ui.test.SemanticsMatcher,
): SemanticsNodeInteraction =
    nodeByTag(parentTag).onChildren().filterToOne(matcher)
