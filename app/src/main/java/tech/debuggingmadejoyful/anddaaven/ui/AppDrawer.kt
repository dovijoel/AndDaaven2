package tech.debuggingmadejoyful.anddaaven.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import tech.debuggingmadejoyful.anddaaven.AndDaavenDestinations
import tech.debuggingmadejoyful.anddaaven.R
import tech.debuggingmadejoyful.anddaaven.R.array.TefillaNameEnum
import tech.debuggingmadejoyful.anddaaven.ui.theme.AndDaavenTheme
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaType

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    modifier: Modifier = Modifier,
    currentTefilla: TefillaType? = null,
    navigateToHome: () -> Unit,
    navigateToTefilla: (TefillaType) -> Unit,
    closeDrawer: () -> Unit,
) {
    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier,
    ) {
        val context = LocalContext.current
        AndDaavenLogo(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.home_title)) },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == AndDaavenDestinations.HOME_ROUTE,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        val tefillaNamesHebrew = stringArrayResource(R.array.TefillaNameHebrew)
        val tefillaNamesEnum = stringArrayResource(TefillaNameEnum)

        for ((index, tefilla) in stringArrayResource(R.array.TefillaNameEnglish).withIndex()) {
            val tefillaType = TefillaType.valueOf(tefillaNamesEnum[index])
            NavigationDrawerItem(
                label = { Row {
                    Text(tefilla)
                    Spacer(Modifier.weight(4f))
                    Text(tefillaNamesHebrew[index], style = TextStyle(textDirection = TextDirection.Rtl))
                } },
                selected = currentRoute == "tech.debuggingmadejoyful.anddaaven.TefillaUri/{tefillaType}" && currentTefilla != null && currentTefilla == tefillaType,
                onClick = { navigateToTefilla(tefillaType); closeDrawer() },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

    }
}

@Composable
private fun AndDaavenLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(R.drawable.ic_launcher_anddaaven),
            contentDescription = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(stringResource(R.string.app_name))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    AndDaavenTheme {
        AppDrawer(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            currentRoute = AndDaavenDestinations.HOME_ROUTE,
            navigateToHome = {},
            navigateToTefilla = {},
            closeDrawer = { }
        )
    }
}