package game.models.scene

import game.interfaces.ITickable
import game.listeners.ISceneListener
import game.listeners.ObservableObject
import game.models.GameMap
import game.models.Player

class Scene(val gameMap: GameMap): ObservableObject<ISceneListener>(), ITickable, IScenePlayersComponent {
    enum class STATUS {
        WAIT_PLAYERS,
        PLAY
    }
    val playersMap = arrayOfNulls<Player>(gameMap.maxPlayers)
    val players: MutableSet<Player> = HashSet()
    val scenePlayersComponent = ScenePlayersComponent(this, gameMap.maxPlayers)
    init {
        scenePlayersComponent.listeners.add(this)
    }

    override fun update(time: Long) {

    }

    var status: STATUS = STATUS.WAIT_PLAYERS
        set(value) {
            if (value == STATUS.PLAY) {
                this.listeners.forEach {it.onPlayScene(scene = this)}
                this.players.forEach { it.listeners.forEach{ it2 -> it2.onSetControlId(it.controlId) }}

            }
            field = value
        }

    override fun onAddedPlayer(player: Player) {
        player.scene = this
        this.listeners.add(player)
        if (this.status == STATUS.PLAY) {
            player.listeners.forEach{ it.onPlay(this)}
            player.listeners.forEach{ it.onSetControlId(player.controlId)}
        }
    }

    override fun onRemovePlayer(player: Player) {
        player.scene = null
        this.listeners.remove(player)
    }
}