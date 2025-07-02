package onl.tesseract.creative.domain.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

open class Board(protected val player: Player) {
    lateinit var scoreboard: Scoreboard
    private lateinit var objective: Objective

    fun update() {
        this.scoreboard = player.scoreboard
        this.objective = scoreboard.getObjective("main") ?: scoreboard.registerNewObjective(
            "main",
            Criteria.DUMMY,
            Component.text("play.tesseract.onl", NamedTextColor.BLUE, TextDecoration.BOLD)
        )

        objective.displaySlot = DisplaySlot.SIDEBAR
        initScoreBoard()
    }

    open fun initScoreBoard() {}

    fun addOrUpdateScore(entry: String, score: Int) {
        scoreboard.entries.forEach { existingEntry ->
            if (objective.getScore(existingEntry).score == score) {
                scoreboard.resetScores(existingEntry)
            }
        }
        val scoreEntry = objective.getScore(entry)
        scoreEntry.score = score
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