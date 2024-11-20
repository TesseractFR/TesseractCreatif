package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.Creatif
import onl.tesseract.nickname.NicknameManager
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.tesseractlib.command.argument.StringArg
import onl.tesseract.tesseractlib.event.ColoredChat
import onl.tesseract.tesseractlib.util.append
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(
    name = "nick",
    args = [Argument(value = "surnom", clazz = StringArg::class, optional = true)]
)
class NickCommand : CommandContext() {

    private val nicknameManager: NicknameManager = Creatif.instance!!.nicknameManager

    @CommandBody
    fun onCommand(
        @Env(key = "surnom") nickname: String?,
        sender: CommandSender
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage(
                Component.text("Cette commande est réservée aux joueurs.")
                    .color(NamedTextColor.RED)
            )
            return false
        }

        if (nickname.isNullOrBlank()) {
            removeNickname(sender)
            sender.sendMessage(
                Component.text("Votre surnom a été supprimé.", NamedTextColor.GREEN)
            )
        } else {
            setNickname(sender, nickname)
            val coloredNickname = ColoredChat.colorMessage(nickname)
            sender.sendMessage(
                Component.text("Votre surnom est maintenant : ", NamedTextColor.GREEN)
                    .append(coloredNickname)
            )
        }
        return true
    }

    private fun setNickname(player: Player, nickname: String) {
        val formattedNickname = ColoredChat.colorComponent(Component.text(nickname))
        player.displayName(Component.text().append(formattedNickname).build())
        player.playerListName(Component.text().append(formattedNickname).build())
        nicknameManager.saveNickname(player.uniqueId, nickname)
    }

    private fun removeNickname(player: Player) {
        val defaultName = Component.text(player.name)
        player.displayName(defaultName)
        player.playerListName(defaultName)
        nicknameManager.saveNickname(player.uniqueId, null)
    }
}
