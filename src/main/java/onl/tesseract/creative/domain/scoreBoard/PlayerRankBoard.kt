package onl.tesseract.creative.domain.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class PlayerRankBoard(
    player: Player,
    private val playerRankService: PlayerRankService,
    private val playerTimePlayedService: PlayerTimePlayedService,
    private val tplayerInfoService: TPlayerInfoService,
) : Board(player) {

    private fun createTimeNextRank() {
        if (getActualRank().ordinal < PlayerRank.BATISSEUR.ordinal) {

            createDynamicLine(
                3, Component.empty(),
                Component.text(getNextRankTime(), NamedTextColor.YELLOW))
            createDynamicLine(
                4, Component.empty(),
                getNextRankName())
            createStaticLine(5, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -")
        }
    }

    private fun getNextRankName(): Component {
        val nextRank = getNextRank()
        val nextRankGender = tplayerInfoService[player.uniqueId].genre
        return Component.text(
            nextRank.title.getDisplayName(nextRankGender)
                    .uppercase(), nextRank.color)
    }

    private fun getNextRankTime(): String {
        val nextRank = getNextRank()
        val remainingTime = playerTimePlayedService.getTimeBeforeRankUp(player.uniqueId, nextRank)
        val remainingTimeFormatted = DurationFormat.formatTime(remainingTime)
        return remainingTimeFormatted
    }

    private fun getNextRank(): PlayerRank {
        return playerRankService.getNextPlayerRank(player.uniqueId)
    }

    private fun getActualRankName(): Component {
        val rank = getActualRank()
        val currentGender = tplayerInfoService[player.uniqueId].genre
        return Component.text(
            rank.title.getDisplayName(currentGender)
                    .uppercase(), rank.color)
    }

    private fun createTimePlayed() {
        createDynamicLine(10, Component.empty(), Component.text(getTimePlayedFormatted(), NamedTextColor.YELLOW))
        createStaticLine(11, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Temps de jeu -")
    }

    private fun getTimePlayedFormatted(): String {
        val timePlayed = playerTimePlayedService.getPlayerTimePlayed(player.uniqueId)
        val timePlayedFormatted = DurationFormat.formatTime(timePlayed)
        return timePlayedFormatted
    }

    private fun createActualRank() {
        createDynamicLine(
            7, Component.empty(),
            getActualRankName())
        createStaticLine(8, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade actuel -")
    }

    private fun createCurrentWorld() {
        createDynamicLine(0, Component.empty(), Component.text(player.world.name, NamedTextColor.YELLOW))
        createStaticLine(1, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -")
    }

    private fun getActualRank(): PlayerRank {
        return playerRankService.getPlayerRank(player.uniqueId)
    }

    override fun setup() {
        createCurrentWorld()
        createStaticLine(2, " ")
        createTimeNextRank()
        createStaticLine(6, " ")
        createActualRank()
        createStaticLine(9, " ")
        createTimePlayed()
    }

    override fun update() {
        updateLine(0, suffix = Component.text(player.world.name, NamedTextColor.YELLOW))
        updateLine(3, suffix = Component.text(getNextRankTime(), NamedTextColor.YELLOW))
        updateLine(4, suffix = getNextRankName())
        updateLine(7, suffix = getActualRankName())
        updateLine(10, suffix = Component.text(getTimePlayedFormatted(), NamedTextColor.YELLOW))
    }
}
