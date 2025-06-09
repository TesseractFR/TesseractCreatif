package onl.tesseract.creative.controller.command.staff

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.creative.SpringComponent
import org.bukkit.command.CommandSender
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.domain.rank.StaffRank
import onl.tesseract.creative.controller.command.argument.StaffRankCommandArg
import onl.tesseract.creative.controller.command.argument.PlayerRankCommandArg
import onl.tesseract.lib.command.argument.PlayerArg
import onl.tesseract.lib.util.append
import org.bukkit.entity.Player

@Command(name = "rank")
@SpringComponent
class RankCommand(
    private val rankService: PlayerRankService,
) {
    @Command
    fun setStaff(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "staffRank", clazz = StaffRankCommandArg::class) rank: StaffRank,
        sender: CommandSender
    ) {
        rankService.setStaffRank(player.uniqueId, rank)

        sender.sendMessage(Component.text("Rang mis à jour.", NamedTextColor.YELLOW))
        player.sendMessage(Component.text("Votre rang a été mis à jour : ", NamedTextColor.YELLOW)
            .append(rank.name, rank.color, TextDecoration.BOLD)
            .append(".",NamedTextColor.YELLOW))
    }

    @Command
    fun resetStaff(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        sender: CommandSender
    ) {
        rankService.setStaffRank(player.uniqueId, null)

        sender.sendMessage(Component.text("Rang réinitialisé.", NamedTextColor.YELLOW))
        player.sendMessage(Component.text("Votre rang a été réinitialisé en membre.", NamedTextColor.YELLOW))
    }

    @Command
    fun setPlayer(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "playerRank", clazz = PlayerRankCommandArg::class ) rank: PlayerRank,
        sender: CommandSender
    ) {
        rankService.setPlayerRank(player.uniqueId, rank)

        sender.sendMessage(Component.text("Rang mis à jour.", NamedTextColor.YELLOW))
        player.sendMessage(Component.text("Votre rang a été mis à jour : ", NamedTextColor.YELLOW)
            .append(rank.name, rank.color, TextDecoration.BOLD)
            .append(".",NamedTextColor.YELLOW))
    }
}
