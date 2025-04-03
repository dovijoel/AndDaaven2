package tech.debuggingmadejoyful.anddaaven

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import tech.debuggingmadejoyful.anddaaven.AndDaavenApplication.Companion.ANDDAAVEN_APP_URI
import tech.debuggingmadejoyful.anddaaven.data.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeRoute
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeViewModel
import tech.debuggingmadejoyful.anddaaven.ui.tefilla.TefillaRoute
import tech.debuggingmadejoyful.anddaaven.ui.tefilla.TefillaViewModel

const val TEFILLA_ID = "tefillaId"

@Composable
fun AndDaavenNavGraph(
    tefillaRepository: TefillaRepository,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    openDrawer: () -> Unit = {},
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
                openDrawer
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
    }
}

@Serializable
data class TefillaUri(val tefillaType: TefillaType)