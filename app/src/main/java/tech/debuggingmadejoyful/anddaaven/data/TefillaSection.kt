package tech.debuggingmadejoyful.anddaaven.data

data class TefillaSection (
    val sectionName: String,
    val paragraphs: List<TefillaSectionParagraph> = emptyList()
)