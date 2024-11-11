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
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MenuMenu(val player: Player) :

    InventoryMenu(54, Component.text("Menu du Créatif", NamedTextColor.RED, TextDecoration.BOLD)) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            10, teteTPWorldMenu,
            Component.text("Téléportations dans les mondes", NamedTextColor.BLUE),
            Component.text("Cliquez pour afficher les différents mondes disponibles et vous y téléporter.", NamedTextColor.GRAY)
            Component.text("Téléportations dans les mondes", NamedTextColor.BLUE, TextDecoration.BOLD),
        ) {
            TPWorldMenu(this).open(viewer)
        }

        addButton(
            11, teteGrades,
            Component.text("Grades", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
            Component.text("Cliquez pour afficher les différents grades du serveur.", NamedTextColor.GRAY)
        ) {
            RankMenu(this).open(viewer)
        }

        addButton(
            12, Material.LIGHT,
            Component.text("Blocs spéciaux", NamedTextColor.DARK_AQUA),
            Component.text("Cliquez pour afficher les différents blocs spéciaux du serveur (hors inventaire).", NamedTextColor.GRAY)
            Component.text("Blocs spéciaux", NamedTextColor.DARK_AQUA, TextDecoration.BOLD),
        ) {
            SpecialBlockMenu(player, this).open(viewer)
        }

        addButton(
            13, Material.WOODEN_AXE,
            Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE),
            Component.text("Cliquez pour afficher les différents outils et plugins utilisés sur le serveur pour construire.", NamedTextColor.GRAY)
            Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
        ) {
            PluginsToolsMenu(this).open(viewer)
        }

        addButton(
            14, Material.BOOK,
            Component.text("Le Build pour les Nuls", NamedTextColor.RED),
            Component.text("Cliquez pour recevoir le guide des commandes de build essentielles pour bien démarrer votre construction !", NamedTextColor.GRAY)
            Component.text("Le Build pour les Nuls", NamedTextColor.RED, TextDecoration.BOLD),
        ) {
            close()
            CommandsBookFactory.getInstance().giveGuideBook(player)
        }

        addButton(
            15, Material.EMERALD,
            Component.text("Boutique de Tesseract", NamedTextColor.YELLOW, TextDecoration.BOLD),
            Component.text("Cliquez pour afficher la boutique de Tesseract.", NamedTextColor.GRAY)
        ) {
            BoutiqueMenu(TPlayer.get(player), this).open(viewer)
        }

        val playerRankService = CreativeServices[PlayerRankService::class.java]
        val playerRank = playerRankService.getPlayerRank(player.uniqueId)

        val timePlayed = CreativeServices[PlayerTimePlayedService::class.java]

        val nbPlots = CreativeServices[PlayerPlotService::class.java]
        val totalPlotsWorld100 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_100)
        val totalPlotsWorld250 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_250)
        val totalPlotsWorld500 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_500)
        val totalPlotsWorld1000 = nbPlots.getPlayerTotalPlot(player.uniqueId, PlotWorld.WORLD_1000)

        addButton(
            16, getHead(player.uniqueId),
            Component.text("Informations du joueur", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            listOf(
                Component.text(""),
                Component.text("Pseudo : ", NamedTextColor.GOLD)
                    .append(player.name, NamedTextColor.WHITE),
                Component.text("Genre : ", NamedTextColor.GOLD)
                    .append(TPlayer.get(player.uniqueId).gender.toString(), NamedTextColor.WHITE),
                Component.text(""),
                Component.text("Grade : ", NamedTextColor.GOLD)
                    .append(playerRank.name, playerRank.color),
                Component.text("Temps de jeu global : ", NamedTextColor.GOLD)
                    .append("time", NamedTextColor.WHITE),
                Component.text(""),
                Component.text("Nombre de plots Monde 100 : ", NamedTextColor.GOLD)
                    .append(totalPlotsWorld100.toString(), NamedTextColor.WHITE),
                Component.text("Nombre de plots Monde 250 : ", NamedTextColor.GOLD)
                    .append(totalPlotsWorld250.toString(), NamedTextColor.WHITE),
                Component.text("Nombre de plots Monde 500 : ", NamedTextColor.GOLD)
                    .append(totalPlotsWorld500.toString(), NamedTextColor.WHITE),
                Component.text("Nombre de plots Monde 1000 : ", NamedTextColor.GOLD)
                    .append(totalPlotsWorld1000.toString(), NamedTextColor.WHITE),
            )
        ) { }

        addButton(
            37, teteInstagram,
            Component.text("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre Instagram.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("----------\n", NamedTextColor.LIGHT_PURPLE)
                    .append("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
                    .append("\n----------", NamedTextColor.LIGHT_PURPLE)
                    .clickEvent(ClickEvent.openUrl("https://www.instagram.com/tesseractfr/"))
            )
        }

        addButton(
            38, teteTiktok,
            Component.text("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre compte TikTok.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("------\n", NamedTextColor.GOLD)
                    .append("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD)
                    .append("\n------", NamedTextColor.GOLD)
                    .clickEvent(ClickEvent.openUrl("https://www.tiktok.com/@tesseractfr?lang=fr"))
            )
        }

        addButton(
            39, teteFacebook,
            Component.text("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre page Facebook.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("---------\n", NamedTextColor.BLUE)
                    .append("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD)
                    .append("\n---------", NamedTextColor.BLUE)
                    .clickEvent(ClickEvent.openUrl("https://www.facebook.com/TesseractFR?locale=fr_FR"))
            )
        }

        addButton(
            40, teteDiscord,
            Component.text("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre Discord.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("--------\n", NamedTextColor.DARK_AQUA)
                    .append("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
                    .append("\n--------", NamedTextColor.DARK_AQUA)
                    .clickEvent(ClickEvent.openUrl("https://discord.gg/4ajRytDJWK"))
            )
        }

        addButton(
            41, teteYoutube,
            Component.text("YouTube", NamedTextColor.RED, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre chaîne YouTube.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("--------\n", NamedTextColor.RED)
                    .append("YouTube", NamedTextColor.RED, TextDecoration.BOLD)
                    .append("\n--------", NamedTextColor.RED)
                    .clickEvent(ClickEvent.openUrl("https://www.youtube.com/@tesseract7852"))
            )
        }

        addButton(
            42, teteTwitter,
            Component.text("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre page X (Twitter).", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("-----------\n", NamedTextColor.AQUA)
                    .append("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD)
                    .append("\n-----------", NamedTextColor.AQUA)
                    .clickEvent(ClickEvent.openUrl("https://x.com/TesseractFR"))
            )
        }

        addButton(
            43, teteSiteWeb,
            Component.text("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre site internet.", NamedTextColor.GRAY)
        ) {
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("-------------\n", NamedTextColor.YELLOW)
                    .append("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD)
                    .append("\n-------------", NamedTextColor.YELLOW)
                    .clickEvent(ClickEvent.openUrl("https://www.tesseract.onl/"))
            )
        }

        addQuitButton()
        super.open(viewer)
    }

    companion object {
        val teteTPWorldMenu: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=",
            "b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25"
        )
        val teteGrades: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=",
            "97e782b408f5456cfad0c43e8c501ef9efd01286297360be3b83fb16dc9cd2a5"
        )
        val teteInstagram: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBkNDY0MTg2ZTFhNTBkZGFhMTRiZTIyNTk2MTFhNGU4NDU4NTE1YTUzNjdhOTM4OWE5Y2M3Yzg5Yzk0YTkzYiJ9fX0=",
            "90d464186e1a50ddaa14be2259611a4e8458515a5367a9389a9cc7c89c94a93b"
        )
        val teteSiteWeb: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZmOGEyMTlmMDgwMzk0MGYxZDI3MzQ5ZmIwNTBjMzJkYzdjMDUwZGIzM2NhMWUwYjM2YzIyZjIxYjA3YmU4NiJ9fX0=",
            "76f8a219f0803940f1d27349fb050c32dc7c050db33ca1e0b36c22f21b07be86"
        )
        val teteFacebook: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGViNDYxMjY5MDQ0NjNmMDdlY2ZjOTcyYWFhMzczNzNhMjIzNTliNWJhMjcxODIxYjY4OWNkNTM2N2Y3NTc2MiJ9fX0=",
            "deb46126904463f07ecfc972aaa37373a22359b5ba271821b689cd5367f75762"
        )
        val teteYoutube: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ4ODU0NWQ1N2M5ZWVkNTJjM2U1NDdlOTZjNDVkYWJiYjdjZjVmOThkNGM4ZmU2MWRjNmY2OWFiYTBhZWY5NiJ9fX0=",
            "3488545d57c9eed52c3e547e96c45dabbb7cf5f98d4c8fe61dc6f69aba0aef96"
        )
        val teteTwitter: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiN2EwYzIxMGU2Y2RmNWEzNWZkODE5N2U2ZTI0YTAzODMxNWJiZTNiZGNkMWJjYzM2MzBiZjI2ZjU5ZWM1YyJ9fX0=",
            "91b7a0c210e6cdf5a35fd8197e6e24a038315bbe3bdcd1bcc3630bf26f59ec5c"
        )
        val teteTiktok: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkMDI5ODRhNDNlNmM2OTEwZDBkOTA4YTU3ZTA0MWMzY2ZiMWRkODgxYjViNzIwYzU1NTYzZTY4MWY1OWUwZSJ9fX0=",
            "58d02984a43e6c6910d0d908a57e041c3cfb1dd881b5b720c55563e681f59e0e"
        )
        val teteDiscord: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=",
            "3b94843d340abadbd6401ef4ec74dcec4b669661065bd1a01f9a5905a8919c72"
        )
    }
} //TODO bouton affichage Info perso (plot/temps de jeu ...)
//TODO bouton menu boutique


