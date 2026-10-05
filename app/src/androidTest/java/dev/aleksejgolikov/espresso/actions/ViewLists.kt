package dev.aleksejgolikov.espresso.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.equalTo

fun recycler(@IdRes recyclerId: Int): ViewInteraction = onView(withId(recyclerId))

fun ViewInteraction.scrollToItem(item: Matcher<View>): ViewInteraction =
    perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(item))

fun ViewInteraction.scrollToItemWithText(text: String): ViewInteraction =
    scrollToItem(anyOf(withText(text), hasDescendant(withText(text))))

fun ViewInteraction.scrollToRow(position: Int): ViewInteraction =
    perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))

fun ViewInteraction.scrollToViewHolder(holder: Matcher<RecyclerView.ViewHolder>): ViewInteraction =
    perform(RecyclerViewActions.scrollToHolder(holder))

fun ViewInteraction.tapItem(item: Matcher<View>): ViewInteraction =
    perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(item, click()))

fun ViewInteraction.tapItemWithText(text: String): ViewInteraction =
    tapItem(anyOf(withText(text), hasDescendant(withText(text))))

fun ViewInteraction.tapRow(position: Int): ViewInteraction =
    perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))

fun adapterItem(
    adapterView: Matcher<View>,
    data: Matcher<Any>,
): DataInteraction = onData(data).inAdapterView(adapterView)

fun adapterItemEquals(
    adapterView: Matcher<View>,
    value: String,
): DataInteraction = adapterItem(adapterView, equalTo(value))
