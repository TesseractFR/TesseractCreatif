package onl.tesseract.creative

import onl.tesseract.core.afk.AfkManager
import onl.tesseract.core.boutique.BoutiqueService
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.core.title.TitleService
import onl.tesseract.lib.chat.ChatEntryService
import onl.tesseract.lib.equipment.EquipmentService
import onl.tesseract.lib.event.EventService
import onl.tesseract.lib.menu.MenuService
import onl.tesseract.lib.persistantcontainer.NamedspacedKeyProvider
import onl.tesseract.lib.profile.PlayerProfileService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.task.TaskScheduler
import org.bukkit.plugin.Plugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.jvm.java

@Configuration
open class CreativeServiceBeans {

    @Bean
    open fun namedSpacedKeyProvider(): NamedspacedKeyProvider {
        return ServiceContainer[NamedspacedKeyProvider::class.java]
    }

    @Bean
    open fun eventService(): EventService {
        return ServiceContainer[EventService::class.java]
    }

    @Bean
    open fun chatEntryService(): ChatEntryService {
        return ServiceContainer[ChatEntryService::class.java]
    }

    @Bean
    open fun equipmentService(): EquipmentService {
        return ServiceContainer[EquipmentService::class.java]
    }

    @Bean
    open fun menuService(): MenuService {
        return ServiceContainer[MenuService::class.java]
    }

    @Bean
    open fun playerProfileService(): PlayerProfileService {
        return ServiceContainer[PlayerProfileService::class.java]
    }

    @Bean
    open fun playerInfoService(): TPlayerInfoService {
        return ServiceContainer[TPlayerInfoService::class.java]
    }

    @Bean
    open fun titleService(): TitleService {
        return ServiceContainer[TitleService::class.java]
    }

    @Bean
    open fun taskService(): TaskScheduler {
        return ServiceContainer[TaskScheduler::class.java]
    }

    @Bean
    open fun boutiqueService(): BoutiqueService {
        return ServiceContainer[BoutiqueService::class.java]
    }

    @Bean
    open fun afkManager(): AfkManager {
        return ServiceContainer[AfkManager::class.java]
    }

    @Bean
    open fun plugin(): Plugin {
        return PLUGIN_INSTANCE
    }
}