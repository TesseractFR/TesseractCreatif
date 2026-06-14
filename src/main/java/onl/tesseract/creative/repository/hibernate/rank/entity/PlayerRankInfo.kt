package onl.tesseract.creative.repository.hibernate.rank.entity

import jakarta.persistence.*
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.rank.PlayerRankInfo
import onl.tesseract.creative.domain.rank.StaffRank
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "t_player_ranks")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class PlayerRankInfoEntity(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    val uuid: UUID,

    @Enumerated(EnumType.STRING)
    var playerRank: PlayerRank = PlayerRank.APPRENTI,

    @Enumerated(EnumType.STRING)
    @Column(name = "staffRank", nullable = true)
    var staffRank: StaffRank? = null,

    @Column(name = "prestigeExpiration", nullable = true)
    var prestige: LocalDateTime? = null,
) {
    fun toDomain(): PlayerRankInfo {
        return PlayerRankInfo(uuid, playerRank, staffRank, prestige)
    }
}

fun PlayerRankInfo.toEntity(): PlayerRankInfoEntity {
    return PlayerRankInfoEntity(uuid, playerRank, staffRank, prestigeRank)
}