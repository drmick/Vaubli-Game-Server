package game.models.scene

import game.listeners.ObservableObject
import game.models.Player

class ScenePlayersComponent(
    val scene: Scene,
    private val maxPlayers: Int): ObservableObject<IScenePlayersComponent>() {

    fun addPlayer(player: Player) {
        if (scene.players.size < maxPlayers) {
            for (i in scene.playersMap.indices) {
                if (scene.playersMap[i] == null) {
                    scene.playersMap[i] = player
                    scene.players.add(player)
                    this.listeners.forEach {it.onAddedPlayer(player)}
                    return
                }
            }
        }
    }

    fun removePlayer(player: Player) {
        for (i in scene.playersMap.indices) {
            if (scene.playersMap[i] == player) {
                scene.playersMap[i] = null
                scene.players.remove(player)
                this.listeners.forEach {it.onRemovePlayer(player)}
                return
            }
        }
    }

}