package tech.debuggingmadejoyful.anddaaven.ui.preferences

import androidx.compose.runtime.Composable

@Composable
fun PreferencesRoute(
    preferencesViewModel: PreferencesViewModel,

) {
    PreferencesScreen(
        preferencesViewModel
        )
}