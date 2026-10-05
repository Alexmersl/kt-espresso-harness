package dev.aleksejgolikov.espresso.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.aleksejgolikov.espresso.demo.MainActivity
import dev.aleksejgolikov.espresso.screens.DemoHomeScreen
import dev.aleksejgolikov.espresso.screens.DemoListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoListTests {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    private val home get() = DemoHomeScreen(compose)
    private val list get() = DemoListScreen(compose)

    @Test
    fun recyclerAndLazyColumnScrollToLateRows() {
        home.assert { homeIsShown() }
        home.openList()
        list.assert { listIsShown() }
        list.scrollRecyclerTo("Row 28")
        list.scrollLazyTo("Lazy 28")
        list.scrollLazyToItem(29)
    }
}
