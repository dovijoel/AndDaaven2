package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.debuggingmadejoyful.anddaaven.data.tefilla.Tefilla

@Composable
fun TefillaComponent(tefilla: Tefilla, listState: LazyListState, fontSize: Float) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            count = tefilla.sections.count(),
            key = {
                tefilla.sections[it].sectionName
            },
            itemContent = { TefillaSectionComponent(tefilla.sections[it], fontSize) }
        )
    }
}