package dev.aleksejgolikov.espresso.framework.compose

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasParent

class ComposeElement(
    internal val rule: ComposeTestRule,
    internal val matcher: SemanticsMatcher,
) {
    fun and(other: ComposeElement) = ComposeElement(rule, matcher.and(other.matcher))

    fun or(other: ComposeElement) = ComposeElement(rule, matcher.or(other.matcher))

    fun descendant(child: ComposeElement) =
        ComposeElement(rule, child.matcher.and(hasAnyAncestor(matcher)))

    fun child(child: ComposeElement) = ComposeElement(rule, child.matcher.and(hasParent(matcher)))

    fun sibling(target: ComposeElement) =
        ComposeElement(rule, target.matcher.and(hasAnySibling(matcher)))

    fun withChild(child: ComposeElement) =
        ComposeElement(rule, matcher.and(hasAnyChild(child.matcher)))

    fun withDescendant(child: ComposeElement) =
        ComposeElement(rule, matcher.and(hasAnyDescendant(child.matcher)))
}

fun ComposeTestRule.byTag(tag: String) = ComposeElement(this, hasTestTag(tag))

fun ComposeTestRule.byText(
    text: String,
    substring: Boolean = false,
) = ComposeElement(this, hasText(text, substring = substring))

fun ComposeTestRule.byDesc(description: String) = ComposeElement(this, hasContentDescription(description))

fun ComposeTestRule.byAll(vararg parts: SemanticsMatcher) =
    ComposeElement(this, parts.reduce { acc, next -> acc.and(next) })

fun ComposeTestRule.byTagAndText(
    tag: String,
    text: String,
    substring: Boolean = false,
) = byTag(tag).and(byText(text, substring))

fun ComposeTestRule.byTagAndDesc(
    tag: String,
    description: String,
) = byTag(tag).and(byDesc(description))

fun ComposeTestRule.byTextAndDesc(
    text: String,
    description: String,
) = byText(text).and(byDesc(description))

fun ComposeTestRule.byTagUnderTag(
    parentTag: String,
    childTag: String,
) = byTag(parentTag).descendant(byTag(childTag))

fun ComposeTestRule.byTextUnderTag(
    parentTag: String,
    text: String,
) = byTag(parentTag).descendant(byText(text))
