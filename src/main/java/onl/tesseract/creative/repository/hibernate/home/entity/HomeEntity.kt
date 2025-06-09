package onl.tesseract.creative.repository.hibernate.home.entity

import jakarta.persistence.Cacheable
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import onl.tesseract.creative.domain.home.Home
import org.hibernate.annotations.CacheConcurrencyStrategy

@Entity
@Table(name = "t_homes")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class HomeEntity(
    @EmbeddedId
    val homePK: HomeEntityPK,

    @Embedded
    val homeLocation: HomeLocationEntity,
) {
    fun toDomain(): Home {
        return Home(
            owner = homePK.uuid,
            name = homePK.name,
            location = homeLocation.toDomain())
    }

}

fun Home.toEntity(): HomeEntity {
    return HomeEntity(HomeEntityPK(owner, name), location.toEntity())
}
