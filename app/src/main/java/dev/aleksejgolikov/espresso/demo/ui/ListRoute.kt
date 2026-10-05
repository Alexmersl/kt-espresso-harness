package dev.aleksejgolikov.espresso.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.aleksejgolikov.espresso.demo.R

private val recyclerRows = (1..40).map { "Row $it" }
private val lazyRows = (1..40).map { "Lazy $it" }

@Composable
fun ListRoute() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .testTag(Tags.LIST_SCREEN)
                .padding(8.dp),
    ) {
        Text("RecyclerView")
        AndroidView(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(220.dp),
            factory = { context ->
                RecyclerView(context).apply {
                    id = R.id.demo_recycler
                    layoutManager = LinearLayoutManager(context)
                    adapter = DemoRowAdapter(recyclerRows)
                }
            },
        )
        Text("LazyColumn")
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag(Tags.LIST_LAZY),
        ) {
            itemsIndexed(lazyRows, key = { index, _ -> index }) { index, title ->
                Text(
                    text = title,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .testTag("${Tags.LIST_ITEM_PREFIX}$index"),
                )
            }
        }
    }
}
