package tech.debuggingmadejoyful.anddaaven.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.AndDaavenDestinations
import tech.debuggingmadejoyful.anddaaven.AndDaavenNavGraph
import tech.debuggingmadejoyful.anddaaven.AndDaavenNavigationActions
import tech.debuggingmadejoyful.anddaaven.data.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.ui.theme.AndDaavenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  AndDaavenApp (
    tefillaRepository: TefillaRepository,
    widthSizeClass: WindowWidthSizeClass,
) {
    AndDaavenTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            AndDaavenNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: AndDaavenDestinations.HOME_ROUTE

        val isExpandedScreen = true
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    drawerState = sizeAwareDrawerState,
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } }
                )
            },
            drawerState = sizeAwareDrawerState,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = !isExpandedScreen
        ) {
            Row {
//                if (isExpandedScreen) {
//                    AppNavRail(
//                        currentRoute = currentRoute,
//                        navigateToHome = navigationActions.navigateToHome,
//                        navigateToInterests = navigationActions.navigateToInterests,
//                    )
//                }
                AndDaavenNavGraph(
                    tefillaRepository = tefillaRepository,
                    isExpandedScreen = isExpandedScreen,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                )
            }
        }
    }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}