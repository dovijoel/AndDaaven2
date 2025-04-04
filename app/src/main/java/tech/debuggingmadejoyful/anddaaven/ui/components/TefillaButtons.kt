package tech.debuggingmadejoyful.anddaaven.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.glance.action.Action
import androidx.glance.appwidget.action.actionStartActivity
import androidx.navigation.compose.rememberNavController
import tech.debuggingmadejoyful.anddaaven.AndDaavenApplication.Companion.ANDDAAVEN_APP_URI
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaType
import tech.debuggingmadejoyful.anddaaven.ui.MainActivity

@Composable
fun TefillaButtons(
    onTefillaSelected: (TefillaType) -> Unit,
    modifier: Modifier = Modifier) {
    val onClick: () -> Unit = {}
    val navController = rememberNavController()
    Column(modifier = modifier) {
        Button(onClick = { onTefillaSelected(TefillaType.SHACHARIT) },
            Modifier
                .fillMaxWidth()
                .padding(5.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text("Shacharit שחרית")
        }
        Button(onClick = { onTefillaSelected(TefillaType.MINCHA) },
            Modifier
                .fillMaxWidth()
                .padding(5.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text("Mincha מנחה")
        }
        Button(onClick = {onTefillaSelected(TefillaType.MAARIV)},
            Modifier
                .fillMaxWidth()
                .padding(5.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text("Ma'ariv מעריב")
        }
        Button(onClick = {onTefillaSelected(TefillaType.BERACHOT)},
            Modifier
                .fillMaxWidth()
                .padding(5.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text("Berachot ברכות")
        }
    }
}

private fun openTefilla(context: Context, tefillaType: TefillaType): Action {
    // actionStartActivity is the preferred way to start activities.
    return actionStartActivity(
        Intent(
            Intent.ACTION_VIEW,
            "$ANDDAAVEN_APP_URI/tefilla?tefillaId=${tefillaType.name}".toUri(),
            context,
            MainActivity::class.java
        )
    )
}