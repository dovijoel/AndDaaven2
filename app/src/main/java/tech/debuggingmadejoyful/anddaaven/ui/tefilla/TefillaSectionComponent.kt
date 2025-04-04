package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaSection

@Composable
fun TefillaSectionComponent(section: TefillaSection, fontSize: Float) {
    Column {
        Row {
            Spacer(Modifier.weight(1f))
            Text(
                text = section.sectionName,
                textDecoration = TextDecoration.Underline,
                style = TextStyle(
                    textAlign = TextAlign.Justify,
                    textDirection = TextDirection.Rtl,
                    fontSize = fontSize.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    section.paragraphs.forEach {
        TefillaParagraphComponent(it.text, fontSize)
    }
}