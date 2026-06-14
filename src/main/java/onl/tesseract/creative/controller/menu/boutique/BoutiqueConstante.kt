package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.BLUE
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.NamedTextColor.GRAY
import net.kyori.adventure.text.format.NamedTextColor.YELLOW
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.controller.menu.boutique.CreativeBoutiqueMenu.Companion.teteVirtuose

fun virtuoseItem(price: Int) = teteVirtuose
        .name("Grade VIRTUOSE", NamedTextColor.AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Nombre de plots maximum :", GRAY, TextDecoration.ITALIC)
        .newline()
        .append("Monde 100 : ", GOLD)
        .append("6", NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Monde 200 : ", GOLD)
        .append("6", NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Monde 500 : ", GOLD)
        .append("4", NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Monde 1000 : ", GOLD)
        .append("2", NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .newline()
        .append("Avantages supplémentaires : ", NamedTextColor.GREEN, TextDecoration.BOLD)
        .newline()
        .append("- Grade ", GOLD, TextDecoration.ITALIC)
        .append("Bâtisseur", BLUE, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
        .newline()
        .append("- Obtention de ", GOLD, TextDecoration.ITALIC)
        .append("Arceon", NamedTextColor.DARK_AQUA, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
        .newline()
        .append("- Commande ", GOLD)
        .append("/nick", NamedTextColor.WHITE, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
        .newline()
        .append("- Et bien plus encore...", GOLD, TextDecoration.ITALIC)
        .newline()
        .newline()
        .append("Prix : ", GRAY)
        .newline()
        .append("$price ", YELLOW, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
        .append("lys d'or", YELLOW, TextDecoration.ITALIC)
        .newline()
        .newline()
        .append("--- Clic gauche ---", NamedTextColor.LIGHT_PURPLE)
        .newline()
        .append("Acheter en lys d'or", NamedTextColor.AQUA)
        .newline()
        .buildLore()
        .build()