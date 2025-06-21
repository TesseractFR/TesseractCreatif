package onl.tesseract.creative.repository.hibernate.nickname.entity

import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import onl.tesseract.creative.domain.nickname.Nickname
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID

@Entity
@Table(name = "t_player_nicknames")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class NicknameEntity(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    val uuid: UUID,

    @Column(nullable = true)
    val nickname: String?,
) {
    fun toDomain(): Nickname {
        return Nickname(player = uuid, nickname = nickname)
    }
}

fun Nickname.toEntity(): NicknameEntity {
    return NicknameEntity(uuid = player, nickname = nickname)
}