package onl.tesseract.creative.repository.hibernate.home.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID

@Embeddable
data class HomeEntityPK(
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    val uuid: UUID,

    @Column(updatable = false, nullable = false)
    val name: String,

    )