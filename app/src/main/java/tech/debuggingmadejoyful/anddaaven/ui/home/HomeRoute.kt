package tech.debuggingmadejoyful.anddaaven.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.debuggingmadejoyful.anddaaven.data.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.components.TefillaButtons

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        TefillaButtons(
            onTefillaSelected = { homeViewModel.navigateToTefilla(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}