package dev.aleksejgolikov.espresso.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import dev.aleksejgolikov.espresso.demo.R
import dev.aleksejgolikov.espresso.demo.ui.Tags
import dev.aleksejgolikov.espresso.framework.compose.byTag
import dev.aleksejgolikov.espresso.framework.compose.byText
import dev.aleksejgolikov.espresso.framework.compose.scrollToTag
import dev.aleksejgolikov.espresso.framework.compose.scrollToText
import dev.aleksejgolikov.espresso.framework.compose.waitShown
import dev.aleksejgolikov.espresso.framework.view.ViewQuery
import dev.aleksejgolikov.espresso.framework.view.scrollToItemWithText
import dev.aleksejgolikov.espresso.framework.view.waitShown

class DemoListScreen(
    private val compose: ComposeTestRule,
) {
    private val root get() = compose.byTag(Tags.LIST_SCREEN)
    private val lazyList get() = compose.byTag(Tags.LIST_LAZY)
    private val recyclerList = ViewQuery.id(R.id.demo_recycler)

    private fun lazyItem(index: Int) = compose.byTag("${Tags.LIST_ITEM_PREFIX}$index")

    private fun lazyRow(title: String) = compose.byText(title)

    private fun recyclerRow(title: String) = ViewQuery.text(title)

    fun scrollLazyTo(title: String) {
        lazyList.scrollToText(title)
        lazyRow(title).waitShown()
    }

    fun scrollLazyToItem(index: Int) {
        lazyList.scrollToTag("${Tags.LIST_ITEM_PREFIX}$index")
        lazyItem(index).waitShown()
    }

    fun scrollRecyclerTo(title: String) {
        recyclerList.waitShown().scrollToItemWithText(title)
        recyclerRow(title).waitShown()
    }

    fun assert(block: Asserts.() -> Unit): DemoListScreen = apply { Asserts().block() }

    inner class Asserts {
        fun listIsShown() {
            root.waitShown()
            lazyList.waitShown()
            recyclerList.waitShown()
        }
    }
}
