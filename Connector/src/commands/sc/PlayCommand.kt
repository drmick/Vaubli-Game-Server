package commands.sc

import com.google.gson.annotations.SerializedName
import commands.ICommand
import game.models.scene.Scene

class PlayCommand(
    @Transient override val command: String = "play",
    @SerializedName("PlayType") val playType: String = "",
    scene: Scene
) : ICommand() {

    @SerializedName("Map")
    var map: Map

    init {
        val cities = HashSet<City>()
        scene.gameMap.cities.forEach {
            cities.add(City(id = it.id, ownerId = it.ownerId, size = it.size))
        }
        map = Map(name = scene.gameMap.name, cities = cities)
    }

    class Map(
        @SerializedName("Name") val name: String = "",
        @SerializedName("Cities") val cities: MutableSet<City>
    ) {
    }

    class City(
        @SerializedName("Id") val id: Int,
        @SerializedName("OwnerId") var ownerId: Int,
        @SerializedName("Size") var size: Int = 0
    )

}
