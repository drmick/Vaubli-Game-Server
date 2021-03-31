package game.services

import game.commands.list.Scenes
import game.GameEngine
import game.models.Player
import game.models.scene.Scene
import kotlin.collections.HashSet


object HubService {
    private val scenes: MutableSet<Scene> = HashSet()
    val waitPlayers: MutableSet<Player> = HashSet()

    private fun getFreeSceneByPlayers(count: Int): Scene {
        var scene = scenes.firstOrNull { (it.gameMap.maxPlayers - it.players.size) >= count }
        if (scene == null) {
            scene = buildRandomScene()
        }
        return scene
    }

    private fun buildRandomScene(): Scene {
        return Templates.getTestScene()
    }

    fun process(gameEngine: GameEngine, currentTime: Long) {
        val playerIterator = waitPlayers.iterator()
        while (playerIterator.hasNext()) {
            val player = playerIterator.next()
            val scene = getFreeSceneByPlayers(1)
            scenes.add(scene)
            scene.scenePlayersComponent.addPlayer(player)
            playerIterator.remove()
        }
        scenes.forEach {
            if (it.gameMap.maxPlayers == it.players.size && it.status == Scene.STATUS.WAIT_PLAYERS) {
                gameEngine.invokeCommand(Scenes.Play(scene = it))
            }
        }

//        if (scenes) {
//
//        }




    }


}

