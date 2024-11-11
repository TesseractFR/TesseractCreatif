package onl.tesseract.command.staff

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import org.bukkit.command.CommandSender
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.StaffRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.command.argument.PlayerArg
import onl.tesseract.command.argument.StaffRankCommandArg
import onl.tesseract.command.argument.PlayerRankCommandArg
import onl.tesseract.tesseractlib.util.append
import org.bukkit.entity.Player

@Command(name = "rank")
class RankCommand : CommandContext() {

    @Command
    fun staff(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "staffRank", clazz = StaffRankCommandArg::class, optional = true) rank: StaffRank?,
        sender: CommandSender
    ) {
        if (rank == null) {
            sender.sendMessage(Component.text("Aucun rang spécifié.", NamedTextColor.RED))
            return
        }

        val playerRankService = CreativeServices[PlayerRankService::class.java]
        playerRankService.setStaffRank(player.uniqueId, rank)

        sender.sendMessage(Component.text("Rang mis à jour.", NamedTextColor.YELLOW))
        player.sendMessage(Component.text("Votre rang a été mis à jour : ", NamedTextColor.YELLOW)
            .append(rank.name, rank.color, TextDecoration.BOLD)
            .append(".",NamedTextColor.YELLOW))
    }

    @Command
    fun player(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "playerRank", clazz = PlayerRankCommandArg::class ) rank: PlayerRank,
        sender: CommandSender
    ) {
        val playerRankService = CreativeServices[PlayerRankService::class.java]
        playerRankService.setPlayerRank(player.uniqueId, rank)

        sender.sendMessage(Component.text("Rang mis à jour.", NamedTextColor.YELLOW))
        player.sendMessage(Component.text("Votre rang a été mis à jour : ", NamedTextColor.YELLOW)
            .append(rank.name, rank.color, TextDecoration.BOLD)
            .append(".",NamedTextColor.YELLOW))
    }
}
