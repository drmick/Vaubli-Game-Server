package game.commands.list

import game.commands.ICommand
import game.models.Player
import game.services.HubService
import org.apache.logging.log4j.LogManager


object HubCommandManager {
    class PlayRandomGame(private val player: Player): ICommand {
        override fun execute() {
            LogManager.getLogger("MAIN").trace("execute Hub.PlayRandomGame")
            HubService.waitPlayers.add(player)
            player.invokeMatchMaking()
        }
    }

    class PlayerDisconnected(private val player: Player) : ICommand {
        override fun execute() {
            LogManager.getLogger("MAIN").trace("execute Hub.PlayerDisconnected")
            HubService.waitPlayers.remove(player)
            val scene = player.scene
            if (scene != null) {
                scene.scenePlayersComponent.removePlayer(player)
                player.scene = null
                player.controlId = -1
            }
        }
    }
}