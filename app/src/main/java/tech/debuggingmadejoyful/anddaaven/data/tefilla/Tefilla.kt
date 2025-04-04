package tech.debuggingmadejoyful.anddaaven.data.tefilla

data class Tefilla(
    val tefillaName: String,
    val sections: List<TefillaSection> = emptyList()
)
