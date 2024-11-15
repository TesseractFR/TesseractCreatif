package onl.tesseract.scoreBoard

import org.bukkit.entity.Player

class PlayerBoard : Board() {

    fun setupBoard(player: Player) {
        initialize()

        addScore("Joueur : ${player.name}", 10)

        applyToPlayer(player)
    }
}