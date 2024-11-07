package onl.tesseract.service

import onl.tesseract.persistence.rank.PlayerRankInfoHibernateRepository
import onl.tesseract.rank.RankService

class CreativeServices private constructor() {
    private val services :MutableMap<Class<*>,Any> = HashMap()

    private fun <S:Any, T: S>registerService(type:Class<S>, service:T) {
        services[type] = service;
    }

    fun <T> getService(type: Class<T>): T {
        val service: Any = services[type] ?: throw IllegalArgumentException("$type not found")
        return service as? T ?: throw IllegalStateException("Service ${service.javaClass.name} incompatible with type ${type.name}")
    }

    fun registerDefaultServices() {
        registerService(RankService::class.java,RankService(PlayerRankInfoHibernateRepository()))
    }

    companion object {
        private var INSTANCE: CreativeServices? = null
        @JvmStatic
        fun getInstance(): CreativeServices {
            if (INSTANCE == null) {
                INSTANCE = CreativeServices()
            }
            return INSTANCE!!
        }

        @JvmStatic
        operator fun <T> get(type: Class<T>): T = getInstance().getService(type)
    }

}