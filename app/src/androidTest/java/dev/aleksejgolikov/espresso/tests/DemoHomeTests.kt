package dev.aleksejgolikov.espresso.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.aleksejgolikov.espresso.actions.waitForPackage
import dev.aleksejgolikov.espresso.demo.MainActivity
import dev.aleksejgolikov.espresso.screens.DemoHomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoHomeTests {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    private val home get() = DemoHomeScreen(compose)

    @Test
    fun homeListAndXmlBannerAreVisible() {
        waitForPackage(compose.activity.packageName)
        home.assert {
            homeIsShown()
            hostBannerIsShown()
        }
    }
}
