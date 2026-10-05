package dev.aleksejgolikov.espresso.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import dev.aleksejgolikov.espresso.demo.ui.DemoTheme
import dev.aleksejgolikov.espresso.demo.ui.HomeRoute
import dev.aleksejgolikov.espresso.demo.ui.ListRoute
import dev.aleksejgolikov.espresso.demo.ui.LoginRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ComposeView>(R.id.compose_host).setContent {
            DemoTheme {
                var screen by rememberSaveable { mutableStateOf(Route.Home) }
                when (screen) {
                    Route.Home ->
                        HomeRoute(
                            onOpenLogin = { screen = Route.Login },
                            onOpenList = { screen = Route.List },
                        )
                    Route.Login -> {
                        BackHandler { screen = Route.Home }
                        LoginRoute(onBack = { screen = Route.Home })
                    }
                    Route.List -> {
                        BackHandler { screen = Route.Home }
                        ListRoute()
                    }
                }
            }
        }
    }
}

private enum class Route { Home, Login, List }
