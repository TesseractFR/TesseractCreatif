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
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

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
        val ilb = ItemLoreBuilder()
            .newline()
            .append("", NamedTextColor.GRAY)
        return ItemBuilder(Material.BLUE_STAINED_GLASS_PANE)
            .name(" ", NamedTextColor.WHITE)
            .lore(ilb.get())
            .build()
    }


    private fun createGradesItemStack(): ItemStack {
        val teteGrades: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=", "97e782b408f5456cfad0c43e8c501ef9efd01286297360be3b83fb16dc9cd2a5")
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
        val teteTPWorldMenu: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=", "b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25")
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

        val ilb = ItemLoreBuilder()
            .newline()
            .append("Pseudo : ", NamedTextColor.GOLD).append(player.name, NamedTextColor.WHITE)
            .newline()
            .append("Genre : ", NamedTextColor.GOLD).append(TPlayer.get(player.uniqueId).gender.getName(), NamedTextColor.WHITE)
            .newline()
            .newline()
            .append("Grade : ", NamedTextColor.GOLD).append(playerRank.name, playerRank.color)
            .newline()
            .append("Temps de jeu global : ", NamedTextColor.GOLD).append("time", NamedTextColor.WHITE)
            .newline()
            .newline()
            .append("Nombre de plots Monde 100 : ", NamedTextColor.GOLD).append(totalPlotsWorld100.toString(), NamedTextColor.WHITE)
            .newline()
            .append("Nombre de plots Monde 250 : ", NamedTextColor.GOLD).append(totalPlotsWorld250.toString(), NamedTextColor.WHITE)
            .newline()
            .append("Nombre de plots Monde 500 : ", NamedTextColor.GOLD).append(totalPlotsWorld500.toString(), NamedTextColor.WHITE)
            .newline()
            .append("Nombre de plots Monde 1000 : ", NamedTextColor.GOLD).append(totalPlotsWorld1000.toString(), NamedTextColor.WHITE)
        return ItemBuilder(getHead(player.uniqueId))
            .name("Informations du joueur", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPlotMenuItemStack(): ItemStack {
        val tetePlotMenu: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmODJhY2ViOThmZTA2OWU4YzE2NmNlZDAwMjQyYTc2NjYwYmJlMDcwOTFjOTJjZGRlNTRjNmVkMTBkY2ZmOSJ9fX0=", "97f82aceb98fe069e8c166ced00242a76660bbe07091c92cdde54c6ed10dcff9")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour afficher vos plots créatifs.", NamedTextColor.GRAY)
        return ItemBuilder(tetePlotMenu)
            .name("Menu des plots", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createGenreSelectionItemStack(): ItemStack {
        val teteGenre: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2EwOGQwZGFiYzQzNGEwOTNmMDk4YmFmNTA1YjE2NWMxNGNiZTk2NDU3M2VkOGU5ZTYxODUxNTg5MTc5NTcwIn19fQ==", "")
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
        val teteInstagram: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBkNDY0MTg2ZTFhNTBkZGFhMTRiZTIyNTk2MTFhNGU4NDU4NTE1YTUzNjdhOTM4OWE5Y2M3Yzg5Yzk0YTkzYiJ9fX0=", "90d464186e1a50ddaa14be2259611a4e8458515a5367a9389a9cc7c89c94a93b")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre Instagram.", NamedTextColor.GRAY)
        return ItemBuilder(teteInstagram)
            .name("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createTiktokItemStack(): ItemStack {
        val teteTiktok: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkMDI5ODRhNDNlNmM2OTEwZDBkOTA4YTU3ZTA0MWMzY2ZiMWRkODgxYjViNzIwYzU1NTYzZTY4MWY1OWUwZSJ9fX0=", "58d02984a43e6c6910d0d908a57e041c3cfb1dd881b5b720c55563e681f59e0e")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre compte TikTok.", NamedTextColor.GRAY)
        return ItemBuilder(teteTiktok)
            .name("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createFacebookItemStack(): ItemStack {
        val teteFacebook: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGViNDYxMjY5MDQ0NjNmMDdlY2ZjOTcyYWFhMzczNzNhMjIzNTliNWJhMjcxODIxYjY4OWNkNTM2N2Y3NTc2MiJ9fX0=", "deb46126904463f07ecfc972aaa37373a22359b5ba271821b689cd5367f75762")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre page Facebook.", NamedTextColor.GRAY)
        return ItemBuilder(teteFacebook)
            .name("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createDiscordItemStack(): ItemStack {
        val teteDiscord: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=", "3b94843d340abadbd6401ef4ec74dcec4b669661065bd1a01f9a5905a8919c72")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre Discord.", NamedTextColor.GRAY)
        return ItemBuilder(teteDiscord)
            .name("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createYoutubeItemStack(): ItemStack {
        val teteYoutube: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ4ODU0NWQ1N2M5ZWVkNTJjM2U1NDdlOTZjNDVkYWJiYjdjZjVmOThkNGM4ZmU2MWRjNmY2OWFiYTBhZWY5NiJ9fX0=", "3488545d57c9eed52c3e547e96c45dabbb7cf5f98d4c8fe61dc6f69aba0aef96")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre chaîne YouTube.", NamedTextColor.GRAY)
        return ItemBuilder(teteYoutube)
            .name("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createTwitterItemStack(): ItemStack {
        val teteTwitter: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiN2EwYzIxMGU2Y2RmNWEzNWZkODE5N2U2ZTI0YTAzODMxNWJiZTNiZGNkMWJjYzM2MzBiZjI2ZjU5ZWM1YyJ9fX0=", "91b7a0c210e6cdf5a35fd8197e6e24a038315bbe3bdcd1bcc3630bf26f59ec5c")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre page X (Twitter).", NamedTextColor.GRAY)
        return ItemBuilder(teteTwitter)
            .name("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createWebsiteItemStack(): ItemStack {
        val teteSiteWeb: ItemStack = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZmOGEyMTlmMDgwMzk0MGYxZDI3MzQ5ZmIwNTBjMzJkYzdjMDUwZGIzM2NhMWUwYjM2YzIyZjIxYjA3YmU4NiJ9fX0=", "76f8a219f0803940f1d27349fb050c32dc7c050db33ca1e0b36c22f21b07be86")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour obtenir le lien vers notre site internet.", NamedTextColor.GRAY)
        return ItemBuilder(teteSiteWeb)
            .name("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    companion object {
        val messageInstagram: Component = Component.text("----------\n", NamedTextColor.LIGHT_PURPLE)
            .append("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .append("\n----------", NamedTextColor.LIGHT_PURPLE)
            .clickEvent(ClickEvent.openUrl("https://www.instagram.com/tesseractfr/"))
        val messageTiktok: Component = Component.text("------\n", NamedTextColor.GOLD)
            .append("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
            .append("\n------", NamedTextColor.GOLD)
            .clickEvent(ClickEvent.openUrl("https://www.tiktok.com/@tesseractfr?lang=fr"))
        val messageFacebook: Component = Component.text("---------\n", NamedTextColor.BLUE)
            .append("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
            .append("\n---------", NamedTextColor.BLUE)
            .clickEvent(ClickEvent.openUrl("https://www.facebook.com/TesseractFR?locale=fr_FR"))
        val messageDiscord: Component = Component.text("--------\n", NamedTextColor.DARK_AQUA)
            .append("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .append("\n--------", NamedTextColor.DARK_AQUA)
            .clickEvent(ClickEvent.openUrl("https://discord.gg/4ajRytDJWK"))
        val messageYoutube: Component = Component.text("--------\n", NamedTextColor.RED)
            .append("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
            .append("\n--------", NamedTextColor.RED)
            .clickEvent(ClickEvent.openUrl("https://www.youtube.com/@tesseract7852"))
        val messageTwitter: Component = Component.text("-----------\n", NamedTextColor.AQUA)
            .append("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
            .append("\n-----------", NamedTextColor.AQUA)
            .clickEvent(ClickEvent.openUrl("https://x.com/TesseractFR"))
        val messageWebsite: Component = Component.text("-------------\n", NamedTextColor.YELLOW)
            .append("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .append("\n-------------", NamedTextColor.YELLOW)
            .clickEvent(ClickEvent.openUrl("https://www.tesseract.onl/"))
    }
}


