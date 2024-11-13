package onl.tesseract.home.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.Getter
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.*


@Embeddable
data class HomePK(
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @field:JdbcTypeCode(Types.VARCHAR)
    var uuid: UUID = UUID.randomUUID(),

    @field:Getter
    var name: String = ""
)