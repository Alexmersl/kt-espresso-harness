package dev.aleksejgolikov.espresso.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import dev.aleksejgolikov.espresso.demo.R
import dev.aleksejgolikov.espresso.demo.ui.Tags
import dev.aleksejgolikov.espresso.framework.compose.byTag
import dev.aleksejgolikov.espresso.framework.compose.byTextUnderTag
import dev.aleksejgolikov.espresso.framework.compose.tap
import dev.aleksejgolikov.espresso.framework.compose.waitShown
import dev.aleksejgolikov.espresso.framework.view.ViewQuery
import dev.aleksejgolikov.espresso.framework.view.waitHasText
import dev.aleksejgolikov.espresso.framework.view.waitShown

class DemoHomeScreen(
    private val compose: ComposeTestRule,
) {
    private val root get() = compose.byTag(Tags.HOME_SCREEN)
    private val loginRow get() = compose.byTag(Tags.HOME_LOGIN_ROW)
    private val listRow get() = compose.byTag(Tags.HOME_LIST_ROW)
    private val listRowLabel get() = compose.byTextUnderTag(Tags.HOME_LIST_ROW, "List Demo")
    private val hostBanner = ViewQuery.id(R.id.host_banner)
    private val hostBannerByIdAndText = ViewQuery.idAndText(R.id.host_banner, "Host banner")

    fun openLogin() {
        loginRow.tap()
    }

    fun openList() {
        listRow.tap()
    }

    fun assert(block: Asserts.() -> Unit): DemoHomeScreen = apply { Asserts().block() }

    inner class Asserts {
        fun homeIsShown() {
            root.waitShown()
            loginRow.waitShown()
            listRow.waitShown()
            listRowLabel.waitShown()
        }

        fun hostBannerIsShown() {
            hostBanner.waitShown()
            hostBannerByIdAndText.waitHasText("Host banner")
        }
    }
}
