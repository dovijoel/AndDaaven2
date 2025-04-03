package tech.debuggingmadejoyful.anddaaven

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import tech.debuggingmadejoyful.anddaaven.data.TefillaType

object AndDaavenDestinations {
    const val HOME_ROUTE = "home"
    const val TEFILLA_ROUTE = "tefilla"
}

class AndDaavenNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(AndDaavenDestinations.HOME_ROUTE)
    }
    val navigateToTefilla: (TefillaType) -> Unit = {
        navController.navigate(route = TefillaUri(it))
    }
}