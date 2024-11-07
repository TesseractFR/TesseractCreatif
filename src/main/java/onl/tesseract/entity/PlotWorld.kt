package onl.tesseract.entity

enum class PlotWorld(private val world: String) {
    WORLD_100("100"),
    WORLD_250("250"),
    WORLD_500("500"),
    WORLD_1000("1000"),
    WORLD_EVENT("Event");

    fun getWorld(): String {
        return world
    }
}
