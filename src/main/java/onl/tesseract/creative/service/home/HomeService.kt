package onl.tesseract.creative.service.home

import onl.tesseract.creative.domain.home.Home
import onl.tesseract.creative.repository.generic.home.HomeRepository
import onl.tesseract.creative.repository.hibernate.home.entity.NoHomeFoundException
import org.bukkit.Location
import org.springframework.stereotype.Service
import java.util.UUID

@Service
open class HomeService(
    private val repository: HomeRepository,
) {
    fun createHome(owner: UUID, name: String, location: Location) {
        val home = Home(owner = owner, name = name, location = location)
        repository.save(home)
    }

    protected fun saveHome(home: Home) {
        repository.save(home)
    }

    fun deleteHome(home: Home) {
        repository.delete(home)
    }

    fun homes(owner: UUID): Set<Home> {
        return repository.getAll(owner);
    }

    @Throws(NoHomeFoundException::class)
    fun getHome(uuid: UUID, name: String): Home {
        val home = repository.getById(Pair(uuid, name))
        require(home != null) { throw NoHomeFoundException("No home found for $uuid with name $name") }
        return home
    }

    fun exist(uuid: UUID, home: String): Boolean {
        return try {
            getHome(uuid, home)
            true
        } catch (e: NoHomeFoundException) {
            false
        }
    }

    fun getAllHomes(owner: UUID): Set<Home> {
        return repository.getAll(owner)
    }

    fun deleteHome(owner: UUID, homeName: String) {
        deleteHome(getHome(owner, homeName))
    }

    fun canCreateHome(uniqueId: UUID): Boolean {
        return true
    }
}