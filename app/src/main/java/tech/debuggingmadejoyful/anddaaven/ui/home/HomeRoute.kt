package tech.debuggingmadejoyful.anddaaven.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import tech.debuggingmadejoyful.anddaaven.R
import tech.debuggingmadejoyful.anddaaven.ui.components.TefillaButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar(
                topAppBarState = topAppBarState,
                openDrawer = openDrawer,
                navigateToSettings = { homeViewModel.navigateToSettings() })
        }
    ) { innerPadding ->
        TefillaButtons(
            onTefillaSelected = { homeViewModel.navigateToTefilla(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? =
        TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    openDrawer: () -> Unit,
    navigateToSettings: () -> Unit
) {
    val title = stringResource(id = R.string.app_name)
    var menuExpanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),

        title = {
            Text(title)
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
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Settings menu"
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navigateToSettings() })
            }
        },
    )
}