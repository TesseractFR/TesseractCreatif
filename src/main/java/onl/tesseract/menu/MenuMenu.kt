package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.CommandsBookFactory
import onl.tesseract.Creatif
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.profile.PlayerProfileService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.lib.util.append
import onl.tesseract.menu.boutique.BoutiqueMenu
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.plot.entity.PlotWorld
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.tesseractlib.menu.GenderMenu
import onl.tesseract.timeplayed.PlayerTimePlayedService
import onl.tesseract.util.DurationFormat
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

private val teteGrades = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=",

    ""
)
private val teteTPWorldMenu = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=",
    ""
)
private val tetePlotMenu = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmODJhY2ViOThmZTA2OWU4YzE2NmNlZDAwMjQyYTc2NjYwYmJlMDcwOTFjOTJjZGRlNTRjNmVkMTBkY2ZmOSJ9fX0=",

    ""
)
private val teteGenre = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2EwOGQwZGFiYzQzNGEwOTNmMDk4YmFmNTA1YjE2NWMxNGNiZTk2NDU3M2VkOGU5ZTYxODUxNTg5MTc5NTcwIn19fQ==",

    ""
)
private val teteInstagram = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBkNDY0MTg2ZTFhNTBkZGFhMTRiZTIyNTk2MTFhNGU4NDU4NTE1YTUzNjdhOTM4OWE5Y2M3Yzg5Yzk0YTkzYiJ9fX0=",

    ""
)
private val teteTiktok = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkMDI5ODRhNDNlNmM2OTEwZDBkOTA4YTU3ZTA0MWMzY2ZiMWRkODgxYjViNzIwYzU1NTYzZTY4MWY1OWUwZSJ9fX0=",
    ""
)
private val teteFacebook = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGViNDYxMjY5MDQ0NjNmMDdlY2ZjOTcyYWFhMzczNzNhMjIzNTliNWJhMjcxODIxYjY4OWNkNTM2N2Y3NTc2MiJ9fX0=",
    ""
)
private val teteDiscord = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=",

    ""
)
private val teteYoutube = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ4ODU0NWQ1N2M5ZWVkNTJjM2U1NDdlOTZjNDVkYWJiYjdjZjVmOThkNGM4ZmU2MWRjNmY2OWFiYTBhZWY5NiJ9fX0=",

    ""
)
private val teteTwitter = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiN2EwYzIxMGU2Y2RmNWEzNWZkODE5N2U2ZTI0YTAzODMxNWJiZTNiZGNkMWJjYzM2MzBiZjI2ZjU5ZWM1YyJ9fX0=",

    ""
)
private val teteSiteWeb = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZmOGEyMTlmMDgwMzk0MGYxZDI3MzQ5ZmIwNTBjMzJkYzdjMDUwZGIzM2NhMWUwYjM2YzIyZjIxYjA3YmU4NiJ9fX0=",
    ""
)
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

    Menu(MenuSize.Six, Component.text("Menu du Créatif", NamedTextColor.RED, TextDecoration.BOLD)) {

    private var infoUpdateTask: BukkitRunnable? = null

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("")
                .build()
        )
        for (slot in listOf(2, 6, 10, 12, 14, 16, 18, 26, 28, 30, 32, 34, 38, 42)) {
            addButton(slot, createBlueStainedGlassPaneItemStack()) {}
        }
        addButton(11, Creatif.instance!!, async = { createGradesItemStack() }) {
            RankMenu(this).open(viewer)
        }
        addButton(15, Creatif.instance!!, async = { createPluginsToolsItemStack() }) {
            PluginsToolsMenu(this).open(viewer)
        }
        addButton(19, Creatif.instance!!, async = { createBuildGuideItemStack() }) {
            close()
            CommandsBookFactory.getInstance().giveGuideBook(player)
        }
        addButton(21, createTPWorldMenuItemStack()) {
            TPWorldMenu(this).open(viewer)
        }

        startUpdatingPlayerInfo(viewer)

        addButton(23, Creatif.instance!!, async = { createPlotMenuItemStack() }) { }
        addButton(25, Creatif.instance!!, async = { createGenreSelectionItemStack() }) {
            GenderMenu(player, this).open(viewer)
        }
        addButton(29, createBoutiqueItemStack()) {
            BoutiqueMenu(player, this).open(viewer)
        }
        addButton(33, createSpecialBlocksItemStack()) {
            SpecialBlockMenu(player, this).open(viewer)
        }
        addButton(46, Creatif.instance!!, async = { createInstagramItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageInstagram)
        }
        addButton(47, Creatif.instance!!, async = { createTiktokItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageTiktok)
        }
        addButton(48, Creatif.instance!!, async = { createFacebookItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageFacebook)
        }
        addButton(50, Creatif.instance!!, async = { createDiscordItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageDiscord)
        }
        addButton(51, Creatif.instance!!, async = { createYoutubeItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageYoutube)
        }
        addButton(52, Creatif.instance!!, async = { createTwitterItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageTwitter)
        }
        addButton(49, Creatif.instance!!, async = { createWebsiteItemStack() }) {
            viewer.closeInventory()
            viewer.sendMessage(messageWebsite)
        }
    }

    private fun startUpdatingPlayerInfo(player: Player) {
        infoUpdateTask = object : BukkitRunnable() {
            override fun run() {
                addButton(22, createPlayerInfoItemStack(player)) { }
            }
        }
        infoUpdateTask?.runTaskTimer(Creatif.instance!!, 0L, 20L)
    }

    private fun onClose(viewer: Player) {
        infoUpdateTask?.cancel()
        infoUpdateTask = null
    }
}

private fun createBlueStainedGlassPaneItemStack(): ItemStack {
    return ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name("")
        .build()
}

private fun createGradesItemStack(): ItemStack {
    return teteGrades
        .name("Grades", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour afficher les différents grades du serveur.", NamedTextColor.GRAY)
        .buildLore()
        .build()
}

private fun createPluginsToolsItemStack(): ItemStack {
    val ilb = ItemLoreBuilder()
        .newline()
        .append(
            "Cliquez pour afficher les différents outils et plugins utilisés sur le serveur pour construire.",
            NamedTextColor.GRAY
        )
    return ItemBuilder(Material.WOODEN_AXE)
        .name("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
        .lore(ilb.get())
        .build()
}

private fun createBuildGuideItemStack(): ItemStack {
    val ilb = ItemLoreBuilder()
        .newline()
        .append(
            "Cliquez pour recevoir le guide des commandes de build essentielles pour bien démarrer votre construction !",
            NamedTextColor.GRAY
        )
    return ItemBuilder(Material.BOOK)
        .name("Le Build pour les Nuls", NamedTextColor.RED, TextDecoration.BOLD)
        .lore(ilb.get())
        .build()
}

private fun createTPWorldMenuItemStack(): ItemStack {
    return teteTPWorldMenu
        .name("Téléportations dans les mondes", NamedTextColor.BLUE, TextDecoration.BOLD)
        .lore()
        .newline()
        .append(
            "Cliquez pour afficher les différents mondes disponibles et vous y téléporter.",
            NamedTextColor.GRAY
            )
        .buildLore()
        .build()
}

 fun createPlayerInfoItemStack(player: Player): ItemStack {
    val playerRankService = ServiceContainer[PlayerRankService::class.java]
    val playerRank = playerRankService.getPlayerRank(player.uniqueId)
    val nbPlots = ServiceContainer[PlayerPlotService::class.java]
    val totalPlotsWorld100 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_100)
    val totalPlotsWorld250 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_250)
    val totalPlotsWorld500 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_500)
    val totalPlotsWorld1000 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_1000)
    val timePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]
    val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)

    val ilb = ItemLoreBuilder()
        .newline()
        .append("Pseudo : ", NamedTextColor.GOLD).append(player.name, NamedTextColor.WHITE)
        .newline()
        .append("Genre : ", NamedTextColor.GOLD)
        .append(
            ServiceContainer[TPlayerInfoService::class.java][player.uniqueId].genre.getName(),
            NamedTextColor.WHITE
        )
        .newline().newline()
        .append("Grade : ", NamedTextColor.GOLD).append(playerRank.name, playerRank.color, TextDecoration.BOLD)
        .newline()
        .append("Temps de jeu global : ", NamedTextColor.GOLD)
        .newline()
        .append(DurationFormat.formatTime(timePlayed), NamedTextColor.WHITE)
        .newline().newline()
        .append("Nombre de plots Monde 100 : ", NamedTextColor.GOLD)
        .append(totalPlotsWorld100.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Nombre de plots Monde 250 : ", NamedTextColor.GOLD)
        .append(totalPlotsWorld250.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Nombre de plots Monde 500 : ", NamedTextColor.GOLD)
        .append(totalPlotsWorld500.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
        .newline()
        .append("Nombre de plots Monde 1000 : ", NamedTextColor.GOLD)
        .append(totalPlotsWorld1000.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
    return ItemBuilder(ServiceContainer[PlayerProfileService::class.java].getPlayerHead(player.uniqueId))
        .name("Informations du joueur", NamedTextColor.YELLOW, TextDecoration.BOLD)
        .lore(ilb.get())
        .build()

}

private fun createPlotMenuItemStack(): ItemStack {
    return tetePlotMenu
        .name("Menu des plots", NamedTextColor.AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
        .append(
            "Fonctionnalité non disponible... pour le moment ;)",
            NamedTextColor.GRAY,
            TextDecoration.ITALIC
        )
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createGenreSelectionItemStack(): ItemStack {
    return teteGenre
        .name("Sélection du genre", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour sélectionner votre genre.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createBoutiqueItemStack(): ItemStack {
    return ItemBuilder(Material.EMERALD)
        .name("Boutique de Tesseract", NamedTextColor.GOLD, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour afficher la boutique de Tesseract.", NamedTextColor.GRAY)
        .buildLore()
        .build()
}

private fun createSpecialBlocksItemStack(): ItemStack {
    return ItemBuilder(Material.LIGHT)
        .name("Blocs spéciaux", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
        .append(
            "Cliquez pour afficher les différents blocs spéciaux du serveur (hors inventaire).",
            NamedTextColor.GRAY
        )
        .buildLore()
        .build()
}

private fun createInstagramItemStack(): ItemStack {
    return teteInstagram
        .name("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre Instagram.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createTiktokItemStack(): ItemStack {
    return teteTiktok
        .name("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre compte TikTok.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createFacebookItemStack(): ItemStack {
    return teteFacebook
        .name("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre page Facebook.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createDiscordItemStack(): ItemStack {
    return teteDiscord
        .name("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre Discord.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createYoutubeItemStack(): ItemStack {
    return teteYoutube
        .name("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre chaîne YouTube.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createTwitterItemStack(): ItemStack {
    return teteTwitter
        .name("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre page X (Twitter).", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}

private fun createWebsiteItemStack(): ItemStack {
    return teteSiteWeb
        .name("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
        .lore()
        .newline()
        .append("Cliquez pour obtenir le lien vers notre site internet.", NamedTextColor.GRAY)
        .buildLore()
        .build(ServiceContainer[PlayerProfileService::class.java])
}



