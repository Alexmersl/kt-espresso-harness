package dev.aleksejgolikov.espresso.framework.view

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withResourceName
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`

/**
 * Espresso locator. Combine with [and], [or], [descendant], [child], [sibling].
 */
class EspressoElement(
    internal val matcher: Matcher<View>,
) {
    fun interaction(): ViewInteraction = onView(matcher)

    fun and(other: EspressoElement) = EspressoElement(allOf(matcher, other.matcher))

    fun or(other: EspressoElement) = EspressoElement(anyOf(matcher, other.matcher))

    fun descendant(child: EspressoElement) = EspressoElement(allOf(child.matcher, isDescendantOfA(matcher)))

    fun child(child: EspressoElement) = EspressoElement(allOf(child.matcher, withParent(matcher)))

    fun sibling(target: EspressoElement) = EspressoElement(allOf(target.matcher, hasSibling(matcher)))

    fun parent() = EspressoElement(withChild(matcher))

    fun hasDescendant(child: EspressoElement) = EspressoElement(allOf(matcher, hasDescendant(child.matcher)))
}

object ViewQuery {
    fun id(@IdRes id: Int) = EspressoElement(withId(id))

    fun text(value: String) = EspressoElement(withText(value))

    fun textContains(value: String) = EspressoElement(withText(containsString(value)))

    fun desc(value: String) = EspressoElement(withContentDescription(value))

    fun hint(value: String) = EspressoElement(withHint(value))

    fun tag(value: Any) = EspressoElement(withTagValue(`is`(value)))

    fun resourceName(name: String) = EspressoElement(withResourceName(name))

    fun clazz(type: Class<out View>) = EspressoElement(isAssignableFrom(type))

    fun all(vararg parts: EspressoElement): EspressoElement {
        require(parts.isNotEmpty())
        return EspressoElement(allOf(parts.map { it.matcher }))
    }

    fun any(vararg parts: EspressoElement): EspressoElement {
        require(parts.isNotEmpty())
        return EspressoElement(anyOf(parts.map { it.matcher }))
    }

    fun idAndText(
        @IdRes id: Int,
        text: String,
    ) = id(id).and(text(text))

    fun idAndDesc(
        @IdRes id: Int,
        description: String,
    ) = id(id).and(desc(description))

    fun idAndHint(
        @IdRes id: Int,
        hint: String,
    ) = id(id).and(hint(hint))

    fun idAndTag(
        @IdRes id: Int,
        tag: Any,
    ) = id(id).and(tag(tag))

    fun textAndDesc(
        text: String,
        description: String,
    ) = text(text).and(desc(description))

    fun textUnderId(
        @IdRes parentId: Int,
        text: String,
    ) = id(parentId).descendant(text(text))

    fun idUnderId(
        @IdRes parentId: Int,
        @IdRes childId: Int,
    ) = id(parentId).descendant(id(childId))
}
