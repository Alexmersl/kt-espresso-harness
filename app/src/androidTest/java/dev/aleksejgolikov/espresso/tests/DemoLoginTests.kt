package dev.aleksejgolikov.espresso.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.aleksejgolikov.espresso.demo.MainActivity
import dev.aleksejgolikov.espresso.screens.DemoHomeScreen
import dev.aleksejgolikov.espresso.screens.DemoLoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoLoginTests {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    private val home get() = DemoHomeScreen(compose)
    private val login get() = DemoLoginScreen(compose)

    @Test
    fun loginFormAcceptsInputThenDeviceBackReturnsHome() {
        home.assert { homeIsShown() }
        home.openLogin()
        login.assert { formIsShown() }
        login.signIn(user = "alice", pass = "secret")
        login.assert { statusIs("Signed in") }
        login.pressSystemBack()
        home.assert { homeIsShown() }
    }
}
