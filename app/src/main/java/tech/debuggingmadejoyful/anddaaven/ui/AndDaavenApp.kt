package tech.debuggingmadejoyful.anddaaven.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.AndDaavenDestinations
import tech.debuggingmadejoyful.anddaaven.AndDaavenNavGraph
import tech.debuggingmadejoyful.anddaaven.AndDaavenNavigationActions
import tech.debuggingmadejoyful.anddaaven.data.preferences.PreferencesRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.theme.AndDaavenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  AndDaavenApp (
    tefillaRepository: TefillaRepository,
    preferencesRepository: PreferencesRepository
) {
    val darkMode = preferencesRepository.currentTheme.collectAsState("AUTO")
    val systemDarkMode = isSystemInDarkTheme()
    val isDarkMode by remember { mutableStateOf(
        if (darkMode.value == "AUTO") systemDarkMode
        else darkMode.value == "DARK"
    )
    }
    AndDaavenTheme(darkTheme = isDarkMode) {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            AndDaavenNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: AndDaavenDestinations.HOME_ROUTE
        val currentTefilla  = if (navBackStackEntry?.arguments?.containsKey("tefillaType") == true)  navBackStackEntry?.arguments?.get("tefillaType") as TefillaType? else null
        val isExpandedScreen = true
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    drawerState = sizeAwareDrawerState,
                    currentRoute = currentRoute,
                    currentTefilla = currentTefilla,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToTefilla = navigationActions.navigateToTefilla,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } }
                )
            },
            drawerState = sizeAwareDrawerState
        ) {
            Row {
                AndDaavenNavGraph(
                    tefillaRepository = tefillaRepository,
                    preferencesRepository = preferencesRepository,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                    saveTextSize = { saveTextSize(coroutineScope, preferencesRepository, it) },
                    textSize = preferencesRepository.currentTextSize.collectAsState(16f).value
                )
            }
        }
    }
}

private fun saveTextSize(coroutineScope: CoroutineScope, preferencesRepository: PreferencesRepository, textSize: Float) {
    coroutineScope.launch { preferencesRepository.saveTextSize(textSize)  }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Open)

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