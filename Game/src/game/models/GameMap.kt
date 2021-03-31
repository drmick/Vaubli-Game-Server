package game.models

class GameMap(
    val maxPlayers: Int,
    val name: String,
    val cities: MutableSet<City> = HashSet()
) : Cloneable