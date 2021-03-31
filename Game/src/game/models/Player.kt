package game.models

import game.listeners.IPlayerListener
import game.listeners.ISceneListener
import game.listeners.ObservableObject
import game.models.scene.Scene

/***
 * @param sessionId = ID пользователя
 */
class Player(
    val sessionId: Long,
    val nickName: String,
    val type: TYPE,
    val perk: String
) :
    ObservableObject<IPlayerListener>(),
    ISceneListener {

    enum class TYPE {
        BOT,
        HUMAN
    }
    // каким городом управляет
    var controlId: Byte = -1
    // в какой сцене играет
    var scene: Scene? = null

    fun invokeMatchMaking() {
        listeners.forEach { it.onMatchMaking() }
    }

    override fun onPlayScene(scene: Scene) {
        listeners.forEach { it.onPlay(scene = scene) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Player
        if (sessionId != other.sessionId) return false
        return true
    }

    override fun hashCode(): Int {
        return sessionId.hashCode()
    }
}

