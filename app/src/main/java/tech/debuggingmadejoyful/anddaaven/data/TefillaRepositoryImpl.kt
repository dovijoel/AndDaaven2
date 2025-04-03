package tech.debuggingmadejoyful.anddaaven.data

import android.content.Context

class TefillaRepositoryImpl(private val applicationContext: Context) : TefillaRepository {
    fun getTefillaPath(type: TefillaType): String {
        when (type) {
            TefillaType.SHACHARIT -> return "shacharit-ashkenaz.utf8"
            TefillaType.MINCHA -> return "mincha-ashkenaz.utf8"
            TefillaType.MINCHA_FAST -> return "minchafast-ashkenaz.utf8"
            TefillaType.MAARIV -> return "maariv-ashkenaz.utf8"
            TefillaType.BERACHOT -> return "other-ashkenaz.utf8"
            TefillaType.ESTHER -> return "ester.utf8"
            TefillaType.CHANUKKA -> return "chanukka.utf8"
        }
    }

    fun getTefillaName(type: TefillaType): String {
        when (type) {
            TefillaType.SHACHARIT -> return "Shacharit"
            TefillaType.MINCHA -> return "Mincha"
            TefillaType.MINCHA_FAST -> return "Mincha - Fast Day"
            TefillaType.MAARIV -> return "Ma'ariv"
            TefillaType.BERACHOT -> return "B'rachot"
            TefillaType.ESTHER -> return "Megillat Esther"
            TefillaType.CHANUKKA -> return "Chanuka"
        }
    }

    override fun getTefilla(type: TefillaType): Result<Tefilla> {
        val fileName = getTefillaPath(type)
        val rawText = applicationContext.assets.open(fileName).bufferedReader().readText()
        val sections: MutableList<TefillaSection> = mutableListOf()
        val rawSections = rawText.split('\u000b')
        for (rawSection in rawSections) {
            val rawParagraphs = rawSection.split("\n")
            if (rawParagraphs.isEmpty()) continue
            val name = rawParagraphs[0]
            if (name.isNotEmpty()) {
                sections.add(
                    TefillaSection(
                        name.trim(),
                        rawParagraphs.drop(1).filter { it.trim().isNotEmpty() }.map { TefillaSectionParagraph(text = it.trim()) })
                )
            } else {
                if (sections.isNotEmpty()) {
                    val section = sections.removeAt(sections.lastIndex)
                    sections.add(
                        section.copy(
                            sectionName = section.sectionName,
                            paragraphs = listOf(
                                section.paragraphs,
                                rawParagraphs.filter { it.trim().isNotEmpty() }
                                    .map { TefillaSectionParagraph(text = it.trim()) }).flatten()
                        )
                    )
                }
            }
        }

        return Result.Success(Tefilla(tefillaName = getTefillaName(type), sections = sections))
    }
}