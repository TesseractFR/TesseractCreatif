package onl.tesseract.rank.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.*

@Entity
@Table(name = "t_player_ranks")
data class PlayerRankInfo (
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
    var staffRank: StaffRank? = null
)