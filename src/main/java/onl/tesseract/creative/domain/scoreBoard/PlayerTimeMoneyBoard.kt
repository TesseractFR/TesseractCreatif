package onl.tesseract.creative.domain.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class PlayerTimeMoneyBoard(
    player: Player,
    private val timePlayedService: PlayerTimePlayedService,
) : Board(player) {

    private fun createCurrentWorld() {
        createDynamicLine(0, Component.empty(), Component.text(player.world.name, NamedTextColor.YELLOW))
        createStaticLine(1, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -")
    }

    private fun createTimePlayed() {
        createDynamicLine(8, Component.empty(), Component.text(getTimePlayedFormatted(), NamedTextColor.YELLOW))
        createStaticLine(9, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Temps de jeu -")
    }

    private fun createLysTemporel() {
        createDynamicLine(
            3,
            Component.text("Prochain : ", NamedTextColor.YELLOW),
            Component.text(getRemainingTimeFormatted(), NamedTextColor.YELLOW))
        createStaticLine(4, "    ")
        createDynamicLine(
            5,
            Component.text("Possédés : ", NamedTextColor.YELLOW),
            Component.text(getTemporalLys(), NamedTextColor.YELLOW))
        createStaticLine(6, "${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Lys temporels -")
    }

    private fun getTemporalLys(): Int {
        return timePlayedService.getTemporalLys(player.uniqueId)
    }

    private fun getRemainingTimeFormatted(): String {
        val nextTemporalLys = timePlayedService.getNextMoneyDuration(player.uniqueId)
        val remainingTimeFormatted = DurationFormat.formatTime(nextTemporalLys)
        return remainingTimeFormatted
    }

    private fun getTimePlayedFormatted(): String {
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        val timePlayedFormatted = DurationFormat.formatTime(timePlayed)
        return timePlayedFormatted
    }

    override fun setup() {
        createCurrentWorld()
        createStaticLine(2, " ")
        createLysTemporel()
        createStaticLine(7, "  ")
        createTimePlayed()
        createStaticLine(10, "   ")


    }

    override fun update() {
        updateLine(0, suffix = Component.text(player.world.name, NamedTextColor.YELLOW))
        updateLine(3, suffix = Component.text(getRemainingTimeFormatted(), NamedTextColor.YELLOW))
        updateLine(5, suffix = Component.text(getTemporalLys(), NamedTextColor.YELLOW))
        updateLine(8, suffix = Component.text(getTimePlayedFormatted(), NamedTextColor.YELLOW))
    }
}
