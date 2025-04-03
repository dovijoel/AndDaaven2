package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.debuggingmadejoyful.anddaaven.data.Tefilla

@Composable
fun TefillaComponent(tefilla: Tefilla) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        tefilla.sections.forEach { section ->
            TefillaSectionComponent(section)
        }
    }
}