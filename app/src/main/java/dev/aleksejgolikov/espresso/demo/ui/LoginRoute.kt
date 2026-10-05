package dev.aleksejgolikov.espresso.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun LoginRoute(onBack: () -> Unit) {
    var user by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .testTag(Tags.LOGIN_SCREEN)
                .padding(16.dp),
    ) {
        Text(text = "Login")
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Username") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(Tags.LOGIN_USERNAME),
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Password") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(Tags.LOGIN_PASSWORD),
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                status = if (user.isNotBlank() && pass.isNotBlank()) "Signed in" else "Need both fields"
            },
            modifier = Modifier.testTag(Tags.LOGIN_SUBMIT),
        ) {
            Text("Sign in")
        }
        Spacer(Modifier.height(8.dp))
        Text(text = status, modifier = Modifier.testTag(Tags.LOGIN_STATUS))
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}
