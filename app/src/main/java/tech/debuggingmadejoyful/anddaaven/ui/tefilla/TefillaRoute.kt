package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TefillaRoute(
    viewModel: TefillaViewModel,
    openDrawer: () -> Unit,
    saveTextSize: (Float) -> Unit,
    textSize: Float,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TefillaRoute(uiState, openDrawer, saveTextSize, textSize)
}

@Composable
fun TefillaRoute(
    uiState: TefillaUiState,
    openDrawer: () -> Unit,
    saveTextSize: (Float) -> Unit,
    textSize: Float,
) {
    TefillaScreen(uiState, openDrawer, saveTextSize, textSize)
}