package onl.tesseract.creative.repository.hibernate.rank.entity

import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.rank.PlayerRankInfo
import onl.tesseract.creative.domain.rank.StaffRank
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID

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
) {
    fun toDomain(): PlayerRankInfo {
        return PlayerRankInfo(uuid, playerRank, staffRank)
    }
}

fun PlayerRankInfo.toEntity(): PlayerRankInfoEntity {
    return PlayerRankInfoEntity(uuid, playerRank, staffRank)
}