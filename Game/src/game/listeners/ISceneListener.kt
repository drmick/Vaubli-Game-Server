package game.listeners

import game.models.scene.Scene

interface ISceneListener {
    fun onPlayScene(scene: Scene)
}
