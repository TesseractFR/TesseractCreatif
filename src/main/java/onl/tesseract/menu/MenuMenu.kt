package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.CommandsBookFactory
import onl.tesseract.menu.boutique.BoutiqueMenu
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.plot.entity.PlotWorld
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.menu.GenderMenu
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.ItemLoreBuilder
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import onl.tesseract.tesseractlib.util.menu.InventoryMenu.getCustomHead
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

private val teteGrades = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=", "")
private val teteTPWorldMenu = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=", "")
private val tetePlotMenu = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmODJhY2ViOThmZTA2OWU4YzE2NmNlZDAwMjQyYTc2NjYwYmJlMDcwOTFjOTJjZGRlNTRjNmVkMTBkY2ZmOSJ9fX0=", "")
private val teteGenre = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2EwOGQwZGFiYzQzNGEwOTNmMDk4YmFmNTA1YjE2NWMxNGNiZTk2NDU3M2VkOGU5ZTYxODUxNTg5MTc5NTcwIn19fQ==", "")
private val teteInstagram = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBkNDY0MTg2ZTFhNTBkZGFhMTRiZTIyNTk2MTFhNGU4NDU4NTE1YTUzNjdhOTM4OWE5Y2M3Yzg5Yzk0YTkzYiJ9fX0=", "")
private val teteTiktok = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkMDI5ODRhNDNlNmM2OTEwZDBkOTA4YTU3ZTA0MWMzY2ZiMWRkODgxYjViNzIwYzU1NTYzZTY4MWY1OWUwZSJ9fX0=", "")
private val teteFacebook = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGViNDYxMjY5MDQ0NjNmMDdlY2ZjOTcyYWFhMzczNzNhMjIzNTliNWJhMjcxODIxYjY4OWNkNTM2N2Y3NTc2MiJ9fX0=", "")
private val teteDiscord = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=", "")
private val teteYoutube = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ4ODU0NWQ1N2M5ZWVkNTJjM2U1NDdlOTZjNDVkYWJiYjdjZjVmOThkNGM4ZmU2MWRjNmY2OWFiYTBhZWY5NiJ9fX0=", "")
private val teteTwitter = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiN2EwYzIxMGU2Y2RmNWEzNWZkODE5N2U2ZTI0YTAzODMxNWJiZTNiZGNkMWJjYzM2MzBiZjI2ZjU5ZWM1YyJ9fX0=", "")
private val teteSiteWeb = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZmOGEyMTlmMDgwMzk0MGYxZDI3MzQ5ZmIwNTBjMzJkYzdjMDUwZGIzM2NhMWUwYjM2YzIyZjIxYjA3YmU4NiJ9fX0=", "")

private val messageInstagram: Component = Component.text("----------\n", NamedTextColor.LIGHT_PURPLE)
    .append("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
    .append("\n----------", NamedTextColor.LIGHT_PURPLE)
    .clickEvent(ClickEvent.openUrl("https://www.instagram.com/tesseractfr/"))
private val messageTiktok: Component = Component.text("------\n", NamedTextColor.GOLD)
    .append("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
    .append("\n------", NamedTextColor.GOLD)
    .clickEvent(ClickEvent.openUrl("https://www.tiktok.com/@tesseractfr?lang=fr"))
private val messageFacebook: Component = Component.text("---------\n", NamedTextColor.BLUE)
    .append("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
    .append("\n---------", NamedTextColor.BLUE)
    .clickEvent(ClickEvent.openUrl("https://www.facebook.com/TesseractFR?locale=fr_FR"))
private val messageDiscord: Component = Component.text("--------\n", NamedTextColor.DARK_AQUA)
    .append("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
    .append("\n--------", NamedTextColor.DARK_AQUA)
    .clickEvent(ClickEvent.openUrl("https://discord.gg/4ajRytDJWK"))
private val messageYoutube: Component = Component.text("--------\n", NamedTextColor.RED)
    .append("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
    .append("\n--------", NamedTextColor.RED)
    .clickEvent(ClickEvent.openUrl("https://www.youtube.com/@tesseract7852"))
private val messageTwitter: Component = Component.text("-----------\n", NamedTextColor.AQUA)
    .append("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
    .append("\n-----------", NamedTextColor.AQUA)
    .clickEvent(ClickEvent.openUrl("https://x.com/TesseractFR"))
private val messageWebsite: Component = Component.text("-------------\n", NamedTextColor.YELLOW)
    .append("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
    .append("\n-------------", NamedTextColor.YELLOW)
    .clickEvent(ClickEvent.openUrl("https://www.tesseract.onl/"))

class MenuMenu(val player: Player) :

    InventoryMenu(54, Component.text("Menu du Créatif", NamedTextColor.RED, TextDecoration.BOLD)) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")
        for (slot in listOf(2, 6, 10, 12, 14, 16, 18, 26, 28, 30, 32, 34, 38, 42)) {
            addButton(slot, createBlueStainedGlassPaneItemStack()) {}
        }
        addButton(11, createGradesItemStack()) {
            RankMenu(this).open(viewer)
        }
        addButton(15, createPluginsToolsItemStack()) {
            PluginsToolsMenu(this).open(viewer)
        }
        addButton(19, createBuildGuideItemStack()) {
            close()
            CommandsBookFactory.getInstance().giveGuideBook(player)
        }
        addButton(21, createTPWorldMenuItemStack()) {
            TPWorldMenu(this).open(viewer)
        }
        addButton(22, createPlayerInfoItemStack(player)) { }
        addButton(23, createPlotMenuItemStack()) { }
        addButton(25, createGenreSelectionItemStack()) {
            GenderMenu(TPlayer.get(player.uniqueId), this).open(viewer)
        }
        addButton(29, createBoutiqueItemStack()) {
            BoutiqueMenu(TPlayer.get(player), this).open(viewer)
        }
        addButton(33, createSpecialBlocksItemStack()) {
            SpecialBlockMenu(player, this).open(viewer)
        }
        addButton(46, createInstagramItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageInstagram)
        }
        addButton(47, createTiktokItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageTiktok)
        }
        addButton(48, createFacebookItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageFacebook)
        }
        addButton(50, createDiscordItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageDiscord)
        }
        addButton(51, createYoutubeItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageYoutube)
        }
        addButton(52, createTwitterItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageTwitter)
        }
        addButton(49, createWebsiteItemStack()) {
            viewer.closeInventory()
            viewer.sendMessage(messageWebsite)
        }
        super.open(viewer)
    }

    private fun createBlueStainedGlassPaneItemStack(): ItemStack {
        return ItemBuilder(Material.BLUE_STAINED_GLASS_PANE)
            .build()
    }

    private fun createGradesItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher les différents grades du serveur.", NamedTextColor.GRAY)
        return ItemBuilder(teteGrades)
            .name("Grades", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPluginsToolsItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher les différents outils et plugins utilisés sur le serveur pour construire.", NamedTextColor.GRAY)
        return ItemBuilder(Material.WOODEN_AXE)
            .name("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createBuildGuideItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour recevoir le guide des commandes de build essentielles pour bien démarrer votre construction !", NamedTextColor.GRAY)
        return ItemBuilder(Material.BOOK)
            .name("Le Build pour les Nuls", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createTPWorldMenuItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher les différents mondes disponibles et vous y téléporter.", NamedTextColor.GRAY)
        return ItemBuilder(teteTPWorldMenu)
            .name("Téléportations dans les mondes", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPlayerInfoItemStack(player: Player): ItemStack {
        val playerRankService = CreativeServices[PlayerRankService::class.java]
        val playerRank = playerRankService.getPlayerRank(player.uniqueId)
        val nbPlots = CreativeServices[PlayerPlotService::class.java]
        val totalPlotsWorld100 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_100)
        val totalPlotsWorld250 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_250)
        val totalPlotsWorld500 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_500)
        val totalPlotsWorld1000 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_1000)
        val timePlayedService = CreativeServices[PlayerTimePlayedService::class.java]
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)

        val ilb = ItemLoreBuilder()
            .newline()
            .append("Pseudo : ", NamedTextColor.GOLD).append(player.name, NamedTextColor.WHITE)
            .newline()
            .append("Genre : ", NamedTextColor.GOLD).append(TPlayer.get(player.uniqueId).gender.getName(), NamedTextColor.WHITE)
            .newline().newline()
            .append("Grade : ", NamedTextColor.GOLD).append(playerRank.name, playerRank.color, TextDecoration.BOLD)
            .newline()
            .append("Temps de jeu global : ", NamedTextColor.GOLD)
            .newline()
            .append(timePlayedService.formatTime(timePlayed), NamedTextColor.WHITE)
            .newline().newline()
            .append("Nombre de plots Monde 100 : ", NamedTextColor.GOLD).append(totalPlotsWorld100.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Nombre de plots Monde 250 : ", NamedTextColor.GOLD).append(totalPlotsWorld250.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Nombre de plots Monde 500 : ", NamedTextColor.GOLD).append(totalPlotsWorld500.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Nombre de plots Monde 1000 : ", NamedTextColor.GOLD).append(totalPlotsWorld1000.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
        return ItemBuilder(getHead(player.uniqueId))
            .name("Informations du joueur", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPlotMenuItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Fonctionnalité non disponible... pour le moment ;)", NamedTextColor.GRAY, TextDecoration.ITALIC)
        return ItemBuilder(tetePlotMenu)
            .name("Menu des plots", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createGenreSelectionItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour sélectionner votre genre.", NamedTextColor.GRAY)
        return ItemBuilder(teteGenre)
            .name("Sélection du genre", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createBoutiqueItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher la boutique de Tesseract.", NamedTextColor.GRAY)
        return ItemBuilder(Material.EMERALD)
            .name("Boutique de Tesseract", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createSpecialBlocksItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher les différents blocs spéciaux du serveur (hors inventaire).", NamedTextColor.GRAY)
        return ItemBuilder(Material.LIGHT)
            .name("Blocs spéciaux", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createInstagramItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre Instagram.", NamedTextColor.GRAY)
        return ItemBuilder(teteInstagram)
            .name("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createTiktokItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre compte TikTok.", NamedTextColor.GRAY)
        return ItemBuilder(teteTiktok)
            .name("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createFacebookItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre page Facebook.", NamedTextColor.GRAY)
        return ItemBuilder(teteFacebook)
            .name("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createDiscordItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre Discord.", NamedTextColor.GRAY)
        return ItemBuilder(teteDiscord)
            .name("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createYoutubeItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre chaîne YouTube.", NamedTextColor.GRAY)
        return ItemBuilder(teteYoutube)
            .name("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createTwitterItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre page X (Twitter).", NamedTextColor.GRAY)
        return ItemBuilder(teteTwitter)
            .name("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createWebsiteItemStack(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre site internet.", NamedTextColor.GRAY)
        return ItemBuilder(teteSiteWeb)
            .name("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    companion object {

    }
}


