package tech.debuggingmadejoyful.anddaaven

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

object AndDaavenDestinations {
    const val HOME_ROUTE = "home"
    const val TEFILLA_ROUTE = "tefilla"
}

class AndDaavenNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(AndDaavenDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToTefilla: (String) -> Unit = {
        navController.navigate(AndDaavenDestinations.TEFILLA_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}