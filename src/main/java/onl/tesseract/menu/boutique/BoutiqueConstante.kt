package onl.tesseract.menu.boutique

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.NamedTextColor.GRAY
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.menu.boutique.CreativeBoutiqueMenu.Companion.teteVirtuose

val virtuoseItem = teteVirtuose
        .name("Grade VIRTUOSE", NamedTextColor.AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
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
        .append("- Grade Bâtisseur", GOLD, TextDecoration.ITALIC)
        .newline()
        .append("- Commande ", GOLD)
        .append("/nick", NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("- Et bien plus encore...", GOLD, TextDecoration.ITALIC)
        .newline()
        .newline()
        .append("Prix : ", GRAY)
        .newline()
        .append("2 500 lys d'or", GOLD, TextDecoration.ITALIC)
        .newline()
        .newline()
        .append("Cliquez pour acheter ", GRAY)
        .newline()
        .buildLore()
        .build()