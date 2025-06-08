package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.service.plot.PlayerPlotService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.service.world.WorldService
import onl.tesseract.lib.profile.PlayerProfileService
import onl.tesseract.creative.controller.menu.MenuMenu
import org.bukkit.entity.Player
import org.springframework.stereotype.Component

@Command(name = "menu")
@Component
class MenuCommand(
    provider: CommandInstanceProvider,
    val worldService: WorldService,
    val playerRankService: PlayerRankService,
    val playerTimePlayedService: PlayerTimePlayedService,
    val playerPlotService: PlayerPlotService,
    val tPlayerInfoService: TPlayerInfoService,
    val playerProfileService: PlayerProfileService,
) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        MenuMenu(
            worldService,
            playerRankService,
            playerTimePlayedService, playerPlotService, tPlayerInfoService, playerProfileService, sender).open(sender)
        return true
    }
}