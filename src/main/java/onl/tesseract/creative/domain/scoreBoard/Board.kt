package onl.tesseract.creative.domain.scoreBoard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.*

abstract class Board(val player: Player) {
    protected val scoreboard: Scoreboard = Bukkit.getScoreboardManager().newScoreboard
    protected val objective: Objective =
        scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, getTitle())
                .apply {
                    displaySlot = DisplaySlot.SIDEBAR
                }

    private val teams = mutableMapOf<Int, Team>()

    init {
        player.scoreboard = scoreboard
    }

    /** Titre du scoreboard */
    fun getTitle(): Component {
        return Component.text("play.tesseract.onl", NamedTextColor.BLUE, TextDecoration.BOLD)
    }

    /** Initialisation des lignes du scoreboard */
    abstract fun setup()

    /** Mise à jour des lignes dynamiques */
    abstract fun update()

    /** Crée une ligne statique, qui ne change jamais */
    protected fun createStaticLine(index: Int, content: String) {
        val entry = content // Unique
        objective.getScore(entry).score = index
    }

    /** Crée une ligne dynamique avec Team */
    protected fun createDynamicLine(index: Int, prefix: Component, suffix: Component = Component.empty()) {
        val entry = ChatColor.COLOR_CHAR.toString() + ('a' + index) // Unique invisible color code
        val team = scoreboard.registerNewTeam("line$index")
                .apply {
                    addEntry(entry)
                    prefix(prefix)
                    suffix(suffix)
                }
        objective.getScore(entry).score = index
        teams[index] = team
    }

    /** Met à jour une ligne dynamique */
    protected fun updateLine(index: Int, prefix: Component? = null, suffix: Component? = null) {
        teams[index]?.let { team ->
            if (prefix != null) team.prefix(prefix)
            if (suffix != null) team.suffix(suffix)
        }
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
