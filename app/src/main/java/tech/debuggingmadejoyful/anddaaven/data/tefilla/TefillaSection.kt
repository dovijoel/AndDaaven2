package tech.debuggingmadejoyful.anddaaven.data.tefilla

data class TefillaSection (
    val sectionName: String,
    val paragraphs: List<TefillaSectionParagraph> = emptyList()
)