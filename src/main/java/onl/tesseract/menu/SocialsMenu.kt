package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class SocialsMenu : InventoryMenu {
    constructor() : super(27, Component.text("Réseaux sociaux", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD))

    constructor(previous: InventoryMenu?) : super(
        27,
        Component.text("Réseaux sociaux", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
        previous
    )

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            3, teteInstagram,
            Component.text("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre Instagram.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("----------\n", NamedTextColor.LIGHT_PURPLE)
                    .append(Component.text("Instagram", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD))
                    .append(Component.text("\n----------", NamedTextColor.LIGHT_PURPLE))
                    .clickEvent(ClickEvent.openUrl("https://www.instagram.com/tesseractfr/"))
            )
        }

        addButton(
            5, teteTiktok,
            Component.text("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre compte TikTok.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("------\n", NamedTextColor.GOLD)
                    .append(Component.text("TikTok", NamedTextColor.GOLD, TextDecoration.BOLD))
                    .append(Component.text("\n------", NamedTextColor.GOLD))
                    .clickEvent(ClickEvent.openUrl("https://www.tiktok.com/@tesseractfr?lang=fr"))
            )
        }

        addButton(
            11, teteFacebook,
            Component.text("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre page Facebook.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("---------\n", NamedTextColor.BLUE)
                    .append(Component.text("Facebook", NamedTextColor.BLUE, TextDecoration.BOLD))
                    .append(Component.text("\n---------", NamedTextColor.BLUE))
                    .clickEvent(ClickEvent.openUrl("https://www.facebook.com/TesseractFR?locale=fr_FR"))
            )
        }

        addButton(
            13, teteDiscord,
            Component.text("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre Discord.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("--------\n", NamedTextColor.DARK_AQUA)
                    .append(Component.text("Discord", NamedTextColor.DARK_AQUA, TextDecoration.BOLD))
                    .append(Component.text("\n--------", NamedTextColor.DARK_AQUA))
                    .clickEvent(ClickEvent.openUrl("https://discord.gg/4ajRytDJWK"))
            )
        }

        addButton(
            15, teteYoutube,
            Component.text("YouTube", NamedTextColor.RED, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre chaîne YouTube.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("--------\n", NamedTextColor.RED)
                    .append(Component.text("YouTube", NamedTextColor.RED, TextDecoration.BOLD))
                    .append(Component.text("\n--------", NamedTextColor.RED))
                    .clickEvent(ClickEvent.openUrl("https://www.youtube.com/@tesseract7852"))
            )
        }

        addButton(
            21, teteTwitter,
            Component.text("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre page X (Twitter).", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("-----------\n", NamedTextColor.AQUA)
                    .append(Component.text("X (Twitter)", NamedTextColor.AQUA, TextDecoration.BOLD))
                    .append(Component.text("\n-----------", NamedTextColor.AQUA))
                    .clickEvent(ClickEvent.openUrl("https://x.com/TesseractFR"))
            )
        }

        addButton(
            23, teteSiteWeb,
            Component.text("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD),
            Component.text("Cliquez pour obtenir le lien vers notre site internet.", NamedTextColor.GRAY)
        ) { event: InventoryClickEvent? ->
            viewer.closeInventory()
            viewer.sendMessage(
                Component.text("-------------\n", NamedTextColor.YELLOW)
                    .append(Component.text("Site Internet", NamedTextColor.YELLOW, TextDecoration.BOLD))
                    .append(Component.text("\n-------------", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://www.tesseract.onl/"))
            )
        }


        addBackButton()
        addQuitButton()

        super.open(viewer)
    }

    companion object {
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
}
