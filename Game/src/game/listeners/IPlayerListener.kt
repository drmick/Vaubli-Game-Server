package game.listeners

import game.models.scene.Scene

interface IPlayerListener {
    fun onMatchMaking()
    fun onPlay(scene: Scene)
    fun onSetControlId(value: Byte)
}
