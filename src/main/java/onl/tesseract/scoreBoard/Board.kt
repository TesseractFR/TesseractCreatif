package onl.tesseract.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.*

open class Board(player: Player?) {
    private lateinit var scoreboard: Scoreboard
    private lateinit var objective: Objective

    init {
        if (player != null) {
            update(player)
        }
    }

    fun update(player: Player) {
        this.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        this.objective = scoreboard.registerNewObjective(
            "main",
            Criteria.DUMMY,
            Component.text("play.tesseract.onl", NamedTextColor.BLUE, TextDecoration.BOLD)
        )
        objective.displaySlot = DisplaySlot.SIDEBAR
        initScoreBoard(player)
    }

    open fun initScoreBoard(player: Player) {
        player.scoreboard = scoreboard
    }

    fun addOrUpdateScore(entry: String, score: Int) {
        scoreboard.entries.forEach { existingEntry ->
            if (objective.getScore(existingEntry).score == score) {
                scoreboard.resetScores(existingEntry)
            }
        }
        val scoreEntry = objective.getScore(entry)
        scoreEntry.score = score
    }

    fun applyToPlayer(player: Player) {
        player.scoreboard = scoreboard
    }

    fun TextColor.toChatColor(): ChatColor {
        return when (this) {
            NamedTextColor.GREEN -> ChatColor.GREEN
            NamedTextColor.LIGHT_PURPLE -> ChatColor.LIGHT_PURPLE
            NamedTextColor.DARK_PURPLE -> ChatColor.DARK_PURPLE
            NamedTextColor.DARK_BLUE -> ChatColor.DARK_BLUE
            NamedTextColor.BLUE -> ChatColor.BLUE
            NamedTextColor.AQUA -> ChatColor.AQUA
            else -> ChatColor.WHITE
        }
    }

}