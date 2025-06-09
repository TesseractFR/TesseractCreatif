package onl.tesseract.creative

import net.milkbowl.vault.permission.Permission
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.creative.controller.command.staff.StaffCommand
import onl.tesseract.lib.TesseractLib
import onl.tesseract.lib.chat.tag.Tag
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.DefaultResourceLoader
import java.util.*

lateinit var PLUGIN_INSTANCE: Creative

class Creative : JavaPlugin(), Listener {
    var permissions: Permission? = null
        private set
    lateinit var springContext: ApplicationContext

    override fun onEnable() {
        PLUGIN_INSTANCE = this
        initSpring()

        registerCommands()
        registerListeners()
        registerTags()

        // Plugin startup logic
        if (!setupPermissions()) {
            System.err.println("Could not setup permissions")
            server.pluginManager.disablePlugin(this)
            return
        }
    }

    private fun initSpring() {
        val classLoader = CompoundClassLoader(
            listOf(classLoader, classLoader.parent, TesseractLib.javaClass.classLoader),
            classLoader.parent)
        val resourceLoader = DefaultResourceLoader(classLoader)
        Thread.currentThread().contextClassLoader = classLoader
        val app = SpringApplication(resourceLoader, CreativeSpringApp::class.java)
        app.setDefaultProperties(mapOf("spring.config.location" to "classpath:/application.properties"))
        app.addInitializers({ applicationContext ->
            val env = applicationContext.environment as ConfigurableEnvironment
            val resource = resourceLoader.getResource("application.properties")
            val props = Properties().apply {
                resource.inputStream.use { load(it) }
            }
            env.propertySources.addFirst(PropertiesPropertySource("customProperties", props))
        })
        this.springContext = app.run()
    }


    fun registerCommands() {
        val provider = springContext.getBean(CreativeCommandInstanceProvider::class.java)
        StaffCommand(provider).register(this, "staff")
        springContext.getBeansOfType(CommandContext::class.java)
                .forEach { (_, bean) -> bean.register(this, bean.commandDefinition.name) }
    }

    fun registerTags() {
        springContext.getBeansOfType(Tag::class.java)
                .forEach { (_, tag) -> Tag.registerTag(tag) }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


    private fun setupPermissions(): Boolean {
        val rsp = server.servicesManager.getRegistration(
            Permission::class.java
        )
        if (rsp == null) return false
        permissions = rsp.provider
        return true
    }

    private fun registerListeners() {
        springContext.getBeansOfType(Listener::class.java)
                .filter { it.value::class.java.name.contains("onl.tesseract.creative") }
                .forEach { (_, bean) -> this.server.pluginManager.registerEvents(bean, this) }
    }
}