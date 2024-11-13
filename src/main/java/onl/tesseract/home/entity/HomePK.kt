package onl.tesseract.home.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.*


@Embeddable
data class HomePK(
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @field:JdbcTypeCode(Types.VARCHAR)
    val uuid: UUID = UUID.randomUUID(),

    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    val name: String = ""
)