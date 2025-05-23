package tech.debuggingmadejoyful.anddaaven

import androidx.navigation.NavController
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaType

object AndDaavenDestinations {
    const val HOME_ROUTE = "home"
    const val PREFERENCES_ROUTE = "preferences"
}

class AndDaavenNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(AndDaavenDestinations.HOME_ROUTE)
    }
    val navigateToTefilla: (TefillaType) -> Unit = {
        navController.navigate(route = TefillaUri(it))
    }
    val navigateToPreferences: () -> Unit = {
        navController.navigate(AndDaavenDestinations.PREFERENCES_ROUTE)
    }
}