package onl.tesseract.home

import onl.tesseract.home.entity.Home
import onl.tesseract.home.entity.HomeLocation
import onl.tesseract.home.entity.HomePK
import onl.tesseract.home.persistence.HomeRepository
import org.bukkit.Location
import java.util.UUID

class HomeService(private val repository: HomeRepository) {

    fun createHome(uuid: UUID, name: String, location: Location) {
        val playerHomes = repository.getAll(uuid)
        val playerHome = playerHomes.find { it.name == name }
        playerHome?.let {
            repository.delete(it.homePK)
        }

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
        val playerHomes = repository.getAll(uuid)
        val playerHome = playerHomes.find {it.name == name}
        playerHome?.let {
            repository.delete(it.homePK)
        }
    }

    fun getHome(uuid: UUID, name: String) : Location? {
        val playerHomes = repository.getAll(uuid)
        val playerHome = playerHomes.find {it.name == name}
        if (playerHome != null) {
            return playerHome.location
        }
        return null
    }

    fun getAllHomes(uuid: UUID) : HashMap<String, Location> {
        val playerHomes = repository.getAll(uuid)
        val homes = HashMap<String, Location>()
        playerHomes.forEach {
            it.location?.let { location ->
                homes[it.name] = location
            }
        }
        return homes
    }

    fun canCreateHome(uuid: UUID): Boolean {
        return true
    }

    fun exist(uuid: UUID, home: String) {
        // A faire
    }
}