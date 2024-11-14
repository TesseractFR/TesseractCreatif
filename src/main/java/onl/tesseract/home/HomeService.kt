package onl.tesseract.home

import onl.tesseract.home.entity.Home
import onl.tesseract.home.entity.HomeLocation
import onl.tesseract.home.entity.HomePK
import onl.tesseract.home.entity.NoHomeFoundException
import onl.tesseract.home.persistence.HomeRepository
import org.bukkit.Location
import java.util.*

class HomeService(private val repository: HomeRepository) {

    fun createHome(uuid: UUID, name: String, location: Location) {
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world.name

        val homePK = HomePK(uuid, name)
        val homeLocation = HomeLocation(x, y, z, world)
        val newHome = Home(homePK, homeLocation)
        repository.save(newHome)
    }


    fun deleteHome(uuid: UUID, name: String) {
        repository.delete(HomePK(uuid, name))
    }
    @Throws(NoHomeFoundException::class)
    fun getHome(uuid: UUID, name: String): Location  {
        val home = repository.getById(HomePK(uuid, name))
        require(home != null) { throw NoHomeFoundException("No home found for $uuid with name $name") }
        return home.location
    }

    fun getAllHomes(uuid: UUID): Set<String> {
        return repository.getAll(uuid)
                .map { it.name }
                .toSet()
    }

    fun canCreateHome(uuid: UUID): Boolean {
        return true
    }

    fun exist(uuid: UUID, home: String) : Boolean {
         try {
             getHome(uuid, home)
             return true
         }catch (e: NoHomeFoundException) {
             return false
         }
    }
}