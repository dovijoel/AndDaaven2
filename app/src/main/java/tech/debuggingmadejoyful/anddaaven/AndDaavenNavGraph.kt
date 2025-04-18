package tech.debuggingmadejoyful.anddaaven

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import tech.debuggingmadejoyful.anddaaven.data.preferences.PreferencesRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeRoute
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeViewModel
import tech.debuggingmadejoyful.anddaaven.ui.preferences.PreferencesRoute
import tech.debuggingmadejoyful.anddaaven.ui.preferences.PreferencesViewModel
import tech.debuggingmadejoyful.anddaaven.ui.tefilla.TefillaRoute
import tech.debuggingmadejoyful.anddaaven.ui.tefilla.TefillaViewModel

const val TEFILLA_ID = "tefillaId"

@Composable
fun AndDaavenNavGraph(
    tefillaRepository: TefillaRepository,
    preferencesRepository: PreferencesRepository,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    openDrawer: () -> Unit = {},
    saveTextSize: (Float) -> Unit,
    textSize: Float,
    startDestination: String = AndDaavenDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<TefillaUri> { navBackStackEntry ->
            val tefillaUri: TefillaUri = navBackStackEntry.toRoute()
            val tefillaViewModel: TefillaViewModel = viewModel(
                factory = TefillaViewModel.provideFactory(
                    tefillaRepository = tefillaRepository,
                    tefillaType = tefillaUri.tefillaType)
                )
            TefillaRoute(
                tefillaViewModel,
                openDrawer,
                saveTextSize,
                textSize
            )
        }
        composable(AndDaavenDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(navController)
            )
            HomeRoute(
                homeViewModel,
                openDrawer
            )
        }
        composable(AndDaavenDestinations.PREFERENCES_ROUTE) {
            val preferencesViewModel: PreferencesViewModel = viewModel(
                factory = PreferencesViewModel.provideFactory(preferencesRepository)
            )
            PreferencesRoute(
                preferencesViewModel
            )
        }
    }
}

@Serializable
data class TefillaUri(val tefillaType: TefillaType)