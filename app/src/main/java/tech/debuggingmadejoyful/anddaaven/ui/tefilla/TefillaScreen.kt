package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TefillaScreen(
    uiState: TefillaUiState
) {
    Scaffold(

    ) { innerPadding ->

        if (uiState.tefilla != null)
            TefillaComponent(
                tefilla = uiState.tefilla
            )
    }
    // TODO app bar

}