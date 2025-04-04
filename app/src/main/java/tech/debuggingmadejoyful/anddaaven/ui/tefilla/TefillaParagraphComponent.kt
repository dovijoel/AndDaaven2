package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TefillaParagraphComponent(text: String, fontSize: Float) {
    val annotatedString = buildAnnotatedString {
        append(text)

    }
    Row (modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))) {
        Spacer(Modifier.weight(1f))
        Text(
            text = annotatedString,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDirection = TextDirection.Rtl,
                fontSize = fontSize.sp
            ),
            modifier = Modifier.padding(PaddingValues(bottom = 2.dp))
        )
    }

}