package onl.tesseract.nickname.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID

@Entity
@Table(name = "t_player_nicknames")
class Nickname(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    var uuid: UUID = UUID.randomUUID(),

    @Column(nullable = true)
    var nickname: String? = null
)
