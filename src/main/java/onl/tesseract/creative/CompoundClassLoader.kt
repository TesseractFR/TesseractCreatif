package onl.tesseract.creative

import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.*
import kotlin.let

class CompoundClassLoader(private val loaders: Collection<ClassLoader>, parent: ClassLoader) : ClassLoader(parent) {

    override fun getResource(name: String): URL? {
        for (loader in loaders) {
            loader.getResource(name)
                    ?.let { return it }
        }
        return null
    }

    override fun getResourceAsStream(name: String): InputStream? {
        for (loader in loaders) {
            loader.getResourceAsStream(name)
                    ?.let { return it }
        }
        return null
    }

    override fun getResources(name: String?): Enumeration<URL> {
        val urls: MutableList<URL> = mutableListOf()

        for (loader in this.loaders) {
            try {
                val resources = loader.getResources(name)

                while (resources.hasMoreElements()) {
                    val resource = resources.nextElement()
                    if (resource != null && !urls.contains(resource)) {
                        urls.add(resource)
                    }
                }
            } catch (_: IOException) {
            }
        }

        return Collections.enumeration(urls)
    }

    override fun loadClass(name: String): Class<*> {
        for (loader in loaders) {
            try {
                return loader.loadClass(name)
            } catch (_: ClassNotFoundException) {

            }
        }
        throw ClassNotFoundException(name)
    }

    override fun loadClass(name: String, resolve: Boolean): Class<*> {
        return this.loadClass(name)
    }
}