package game.services

import game.models.City
import game.models.GameMap
import game.models.scene.Scene

/**
 * Created by Maks on 20.04.2019.
 */
object Templates {
    fun getPerk(): String {
        return arrayListOf("dictatorship", "freedomOfSpeech", "plannedEconomy").random()
    }

    fun getTestScene(): Scene {
        return Scene(gameMap = getTrislandosMap())
    }

    private fun getTrislandosMap(): GameMap {
        val gameMap = GameMap(
            name = "Trislandos",
            maxPlayers = 3
        )
        gameMap.cities.add(City(id = 1, ownerId = 1, size = 10))
        gameMap.cities.add(City(id = 2, ownerId = 1, size = 20))
        gameMap.cities.add(City(id = 3, ownerId = 1, size = 30))
        gameMap.cities.add(City(id = 4, ownerId = 1, size = 40))
        gameMap.cities.add(City(id = 5, ownerId = 1, size = 50))
        gameMap.cities.add(City(id = 6, ownerId = 2, size = 10))
        gameMap.cities.add(City(id = 7, ownerId = 2, size = 20))
        gameMap.cities.add(City(id = 8, ownerId = 2, size = 30))
        gameMap.cities.add(City(id = 9, ownerId = 2, size = 40))
        gameMap.cities.add(City(id = 10, ownerId = 2, size = 50))
        gameMap.cities.add(City(id = 11, ownerId = 0, size = 10))
        gameMap.cities.add(City(id = 12, ownerId = 0, size = 20))
        gameMap.cities.add(City(id = 13, ownerId = 0, size = 30))
        gameMap.cities.add(City(id = 14, ownerId = 0, size = 40))
        gameMap.cities.add(City(id = 15, ownerId = 0, size = 50))
        return gameMap
    }
}