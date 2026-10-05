package dev.aleksejgolikov.espresso.demo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun HomeRoute(
    onOpenLogin: () -> Unit,
    onOpenList: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .testTag(Tags.HOME_SCREEN)
                .padding(16.dp),
    ) {
        Text(text = "Demo home")
        ListItem(
            headlineContent = { Text("Login Screen") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(Tags.HOME_LOGIN_ROW)
                    .clickable(onClick = onOpenLogin),
        )
        HorizontalDivider()
        ListItem(
            headlineContent = { Text("List Demo") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(Tags.HOME_LIST_ROW)
                    .clickable(onClick = onOpenList),
        )
    }
}
