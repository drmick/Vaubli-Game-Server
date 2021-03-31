package game.models.scene

import game.models.Player

interface IScenePlayersComponent {
    fun onAddedPlayer(player: Player)
    fun onRemovePlayer(player: Player)

}
