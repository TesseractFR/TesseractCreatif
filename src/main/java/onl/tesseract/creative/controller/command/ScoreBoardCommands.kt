package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.creative.SpringComponent
import onl.tesseract.creative.service.scoreboard.ScoreBoardService
import org.bukkit.entity.Player
import org.springframework.stereotype.Component

@Command(name = "scoreboard")
@SpringComponent
class ScoreBoardCommands(
    provider: CommandInstanceProvider,
    private val scoreBoardCore: ScoreBoardService,
) : CommandContext(provider) {

    @Command(name = "afficher")
    fun enableScoreBoard(sender: Player) {
        scoreBoardCore.showScoreboard(sender)
    }

    @Command(name = "masquer")
    fun disableScoreBoard(sender: Player) {
        scoreBoardCore.hideScoreboard(sender)
    }
}