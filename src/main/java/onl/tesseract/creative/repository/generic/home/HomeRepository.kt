package onl.tesseract.creative.repository.generic.home

import onl.tesseract.creative.domain.home.Home
import onl.tesseract.creative.repository.generic.Repository
import java.util.UUID

interface HomeRepository : Repository<Home, Pair<UUID, String>> {
    fun delete(home: Home)
    fun getAll(uuid: UUID): Set<Home>
}