package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TefillaRoute(
    viewModel: TefillaViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TefillaRoute(uiState)
}

@Composable
fun TefillaRoute(
    uiState: TefillaUiState
) {
    TefillaScreen(uiState)
}