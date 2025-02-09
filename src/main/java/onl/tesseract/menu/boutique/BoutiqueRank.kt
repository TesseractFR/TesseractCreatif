package onl.tesseract.menu.boutique

import onl.tesseract.rank.entity.PlayerRank

enum class BoutiqueRank(val price: Int, val hourPrice: Int, val playerRank: PlayerRank, val strName: String) {
    APPRENTI(0, 0, PlayerRank.APPRENTI, "Apprenti"),
    CONCEPTEUR(100, 25, PlayerRank.CONCEPTEUR, "Concepteur"),
    CREATEUR(300, 19, PlayerRank.CREATEUR, "Createur"),
    INGENIEUR(800, 17, PlayerRank.INGENIEUR, "Ingenieur"),
    BATISSEUR(2000, 12, PlayerRank.BATISSEUR, "Batisseur"),
    VIRTUOSE(2500, 12, PlayerRank.VIRTUOSE, "Virtuose"),
    ;


    companion object {
        fun fromPlayerRank(playerRank: PlayerRank): BoutiqueRank {
            for (item in entries) {
                if (item.playerRank == playerRank) {
                    return item
                }
            }
            throw IllegalArgumentException("BoutiqueRank $playerRank not supported")
        }
    }
}