package dev.aleksejgolikov.espresso.actions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.TypeSafeMatcher

fun viewById(@IdRes id: Int): ViewInteraction = onView(withId(id))

fun viewByText(text: String): ViewInteraction = onView(withText(text))

fun viewByTextContains(substring: String): ViewInteraction = onView(withText(containsString(substring)))

fun viewByDesc(description: String): ViewInteraction = onView(withContentDescription(description))

fun viewByHint(hint: String): ViewInteraction = onView(withHint(hint))

fun viewMatching(matcher: Matcher<View>): ViewInteraction = onView(matcher)

/** Any depth under [parent]. */
fun descendantOf(
    parent: Matcher<View>,
    child: Matcher<View>,
): ViewInteraction = onView(allOf(child, isDescendantOfA(parent)))

fun descendantOfId(
    @IdRes parentId: Int,
    child: Matcher<View>,
): ViewInteraction = descendantOf(withId(parentId), child)

/** Direct child of [parent]. */
fun childOf(
    parent: Matcher<View>,
    child: Matcher<View>,
): ViewInteraction = onView(allOf(child, withParent(parent)))

fun childOfId(
    @IdRes parentId: Int,
    child: Matcher<View>,
): ViewInteraction = childOf(withId(parentId), child)

fun nthChildOf(
    parent: Matcher<View>,
    index: Int,
): ViewInteraction = onView(nthChildMatcher(parent, index))

fun nthChildOfId(
    @IdRes parentId: Int,
    index: Int,
): ViewInteraction = nthChildOf(withId(parentId), index)

fun siblingOf(
    sibling: Matcher<View>,
    target: Matcher<View>,
): ViewInteraction = onView(allOf(target, hasSibling(sibling)))

fun siblingOfText(
    siblingText: String,
    target: Matcher<View>,
): ViewInteraction = siblingOf(withText(siblingText), target)

fun parentOf(child: Matcher<View>): ViewInteraction = onView(withChild(child))

fun withDescendantText(
    @IdRes parentId: Int,
    text: String,
): ViewInteraction = onView(allOf(withId(parentId), hasDescendant(withText(text))))

fun nthChildMatcher(
    parentMatcher: Matcher<View>,
    index: Int,
): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("child at index $index of parent ")
            parentMatcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            val parent = view.parent as? ViewGroup ?: return false
            return parentMatcher.matches(parent) && index in 0 until parent.childCount && parent.getChildAt(index) === view
        }
    }
