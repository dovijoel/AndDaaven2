package tech.debuggingmadejoyful.anddaaven.data

data class Tefilla(
    val tefillaName: String,
    val sections: List<TefillaSection> = emptyList()
)
