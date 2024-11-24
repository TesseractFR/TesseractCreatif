package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.scoreBoard.ScoreBoardCore
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(name = "scoreboard")
class ScoreBoardCommands : CommandContext() {

    @Command(name = "afficher")
    fun enableScoreBoard(sender: CommandSender) {
        if (sender is Player) {
            ScoreBoardCore.showScoreboard(sender)
        }
    }

    @Command(name = "masquer")
    fun disableScoreBoard(sender: CommandSender) {
        if (sender is Player) {
            ScoreBoardCore.hideScoreboard(sender)
        }
    }
}