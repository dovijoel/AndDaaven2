package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.AndDaavenDestinations
import tech.debuggingmadejoyful.anddaaven.R
import tech.debuggingmadejoyful.anddaaven.data.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.home.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TefillaScreen(
    uiState: TefillaUiState,
    openDrawer: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            if (uiState.tefilla != null && uiState.tefilla.tefillaName.isNotEmpty())
                TefillaTopAppBar(
                    uiState.tefilla.tefillaName,
                    topAppBarState = topAppBarState,
                    openDrawer = openDrawer,
                    openSectionsDrawer = { coroutineScope.launch { drawerState.open() } },
                )
        }
    ) { innerPadding ->
        val listState = rememberLazyListState()
        if (uiState.tefilla != null)
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        if (uiState.tefilla.sections.isNotEmpty())
                            TefillaSectionsNavDrawer(
                                drawerState = drawerState,
                                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                                sections = uiState.tefilla.sections.map { it.sectionName },
                                scrollToSection = { scrollToSection(coroutineScope, listState, it) }
                            )
                    },
                    modifier = Modifier.padding(innerPadding)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        val tefilla = remember { uiState.tefilla }
                        TefillaComponent(
                            tefilla = tefilla,
                            listState
                        )
                    }
                }
            }
    }
}

private fun scrollToSection(
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    index: Int
) {
    coroutineScope.launch { lazyListState.animateScrollToItem(index) }
}

@Composable
fun TefillaSectionsNavDrawer(
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    sections: List<String>,
    scrollToSection: (Int) -> Unit
) {
    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier,
    ) {
        LazyColumn {
            items(sections.count()) {
                NavigationDrawerItem(
                    label = { Text(sections[it]) },
                    selected = false,
                    onClick = { scrollToSection(it); closeDrawer() },
                )
            }
        }
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
    openSectionsDrawer: () -> Unit,
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
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Settings menu"
                )
            }
            IconButton(onClick = { openSectionsDrawer() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Settings menu"
                )
            }
        },
    )
}