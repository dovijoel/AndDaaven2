package tech.debuggingmadejoyful.anddaaven.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.debuggingmadejoyful.anddaaven.AndDaavenApplication
import tech.debuggingmadejoyful.anddaaven.ui.components.TefillaButtons
import tech.debuggingmadejoyful.anddaaven.ui.theme.AndDaavenTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tefillaRepository = (application as AndDaavenApplication).tefillaRepository

        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

            AndDaavenApp(tefillaRepository, widthSizeClass)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndDaavenTheme {
        TefillaButtons({})
    }
}