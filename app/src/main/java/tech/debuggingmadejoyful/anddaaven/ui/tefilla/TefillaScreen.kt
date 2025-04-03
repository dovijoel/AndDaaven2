package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import tech.debuggingmadejoyful.anddaaven.R
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TefillaScreen(
    uiState: TefillaUiState,
    openDrawer: () -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    Scaffold(
    topBar = {
        if (uiState.tefilla != null && uiState.tefilla.tefillaName.isNotEmpty())
        TefillaTopAppBar(uiState.tefilla.tefillaName, topAppBarState = topAppBarState, openDrawer = openDrawer) }
    ) { innerPadding ->

        if (uiState.tefilla != null)
            TefillaComponent(
                tefilla = uiState.tefilla,
                innerPadding
            )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TefillaTopAppBar(
    tefilla: String,
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? =
        TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    openDrawer: () -> Unit,
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),

        title = {
            Text(tefilla)
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_anddaaven),
                    contentDescription = stringResource(R.string.cd_open_navigation_drawer)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Settings menu"
                )
            }
        },
    )
}