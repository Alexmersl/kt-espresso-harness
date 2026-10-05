package dev.aleksejgolikov.espresso.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.platform.app.InstrumentationRegistry
import dev.aleksejgolikov.espresso.actions.deviceBack
import dev.aleksejgolikov.espresso.demo.ui.Tags
import dev.aleksejgolikov.espresso.framework.compose.byTag
import dev.aleksejgolikov.espresso.framework.compose.byTagAndText
import dev.aleksejgolikov.espresso.framework.compose.tap
import dev.aleksejgolikov.espresso.framework.compose.type
import dev.aleksejgolikov.espresso.framework.compose.waitShown
import dev.aleksejgolikov.espresso.framework.nativeui.NativeElement
import dev.aleksejgolikov.espresso.framework.nativeui.NativeQuery
import dev.aleksejgolikov.espresso.framework.nativeui.exists

class DemoLoginScreen(
    private val compose: ComposeTestRule,
) {
    private val root get() = compose.byTag(Tags.LOGIN_SCREEN)
    private val username get() = compose.byTag(Tags.LOGIN_USERNAME)
    private val password get() = compose.byTag(Tags.LOGIN_PASSWORD)
    private val submit get() = compose.byTag(Tags.LOGIN_SUBMIT)
    private val status get() = compose.byTag(Tags.LOGIN_STATUS)
    private val nativeLoginRoot: NativeElement
        get() {
            val pkg = InstrumentationRegistry.getInstrumentation().targetContext.packageName
            return NativeQuery.res("$pkg:id/compose_host")
        }

    fun signIn(
        user: String,
        pass: String,
    ) {
        username.type(user)
        password.type(pass)
        submit.tap()
    }

    fun pressSystemBack() {
        deviceBack()
    }

    fun assert(block: Asserts.() -> Unit): DemoLoginScreen = apply { Asserts().block() }

    inner class Asserts {
        fun formIsShown() {
            root.waitShown()
            username.waitShown()
            password.waitShown()
            submit.waitShown()
            check(nativeLoginRoot.exists()) { "Native host ComposeView is missing" }
        }

        fun statusIs(text: String) {
            compose.byTagAndText(Tags.LOGIN_STATUS, text).waitShown()
        }
    }
}
