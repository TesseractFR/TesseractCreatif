package onl.tesseract.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.*

open class Board {
    private val scoreboard: Scoreboard = Bukkit.getScoreboardManager().newScoreboard

    private lateinit var objective: Objective

    fun initialize() {
        objective = scoreboard.registerNewObjective(
            "main",
            Criteria.DUMMY,
            Component.text("Bienvenue sur le Créatif", NamedTextColor.GREEN, TextDecoration.BOLD),
        )
        objective.displaySlot = DisplaySlot.SIDEBAR
    }

    fun addScore(entry: String, score: Int) {
        val scoreEntry = objective.getScore(entry)
        scoreEntry.score = score
    }

    fun applyToPlayer(player: Player) {
        player.scoreboard = scoreboard
    }
}