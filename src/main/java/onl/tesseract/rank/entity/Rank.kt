package onl.tesseract.rank.entity

import net.kyori.adventure.text.format.TextColor
import onl.tesseract.core.title.Title

interface Rank {
    val permGroup: String
    val title: Title
    val color: TextColor
}