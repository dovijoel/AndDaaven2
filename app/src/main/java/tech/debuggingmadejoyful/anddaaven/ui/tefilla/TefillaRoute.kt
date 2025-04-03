package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TefillaRoute(
    viewModel: TefillaViewModel,
    openDrawer: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TefillaRoute(uiState, openDrawer)
}

@Composable
fun TefillaRoute(
    uiState: TefillaUiState,
    openDrawer: () -> Unit,
) {
    TefillaScreen(uiState, openDrawer)
}