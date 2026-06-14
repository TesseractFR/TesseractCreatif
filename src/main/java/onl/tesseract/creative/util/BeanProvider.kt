package onl.tesseract.creative.util

import onl.tesseract.core.boutique.BoutiqueService
import onl.tesseract.creative.PLUGIN_INSTANCE
import onl.tesseract.creative.service.permpack.PlayerPermPackService
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.plot.PlayerPlotService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.lib.menu.MenuService
import onl.tesseract.lib.profile.PlayerProfileService

fun menuService(): MenuService =
    PLUGIN_INSTANCE.springContext.getBean(MenuService::class.java)

fun playerTimePlayedService(): PlayerTimePlayedService =
    PLUGIN_INSTANCE.springContext.getBean(PlayerTimePlayedService::class.java)

fun boutiqueService(): BoutiqueService =
    PLUGIN_INSTANCE.springContext.getBean(BoutiqueService::class.java)

fun permPackService(): PlayerPermPackService =
    PLUGIN_INSTANCE.springContext.getBean(PlayerPermPackService::class.java)

fun playerRankService(): PlayerRankService =
    PLUGIN_INSTANCE.springContext.getBean(PlayerRankService::class.java)

fun permissionService(): PermissionService =
    PLUGIN_INSTANCE.springContext.getBean(PermissionService::class.java)

fun playerPlotService(): PlayerPlotService =
    PLUGIN_INSTANCE.springContext.getBean(PlayerPlotService::class.java)

fun playerProfileService(): PlayerProfileService =
    PLUGIN_INSTANCE.springContext.getBean(PlayerProfileService::class.java)