package tech.debuggingmadejoyful.anddaaven.ui.preferences

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.ui.theme.AndDaavenTheme

@Composable
fun PreferencesScreen(
    preferencesViewModel: PreferencesViewModel
) {
    val uiState by preferencesViewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    val currentThemeText by remember {
        mutableStateOf(
            when (uiState.currentTheme) {
                "AUTO" -> "System/Auto"
                "LIGHT" -> "Light mode"
                "DARK" -> "Dark mode"
                else -> "System/Auto"
            }
        )
    }
    val isThemeDropDownExpanded = remember {
        mutableStateOf(false)
    }
    Scaffold { innerPadding -> {

    } }
    Column {
        Box {
            Text("Appearance", fontSize = 20.sp)
            Row {
                Text(currentThemeText)
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Drop down arrow"
                )
            }
        }
        DropdownMenu(
            expanded = isThemeDropDownExpanded.value,
            onDismissRequest = { isThemeDropDownExpanded.value = false }
        ) {
            DropdownMenuItem(
                text = { Text("System/Auto") },
                onClick = { coroutineScope.launch { preferencesViewModel.updateTheme("AUTO") } }
            )
            DropdownMenuItem(
                text = { Text("Light") },
                onClick = { coroutineScope.launch { preferencesViewModel.updateTheme("LIGHT") }  }
            )
            DropdownMenuItem(
                text = { Text("Dark") },
                onClick = { coroutineScope.launch { preferencesViewModel.updateTheme("DARK") }  }
            )
        }
    }
}