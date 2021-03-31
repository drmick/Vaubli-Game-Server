package listeners

import commands.sc.ControlCommand
import commands.sc.MatchMakingCommand
import commands.sc.PlayCommand
import commands.sc.PlayersCommand
import game.listeners.IPlayerListener
import game.models.scene.Scene
import models.User
import org.apache.logging.log4j.LogManager


class GamePlayerListener(val user: User) : IPlayerListener {
    override fun onMatchMaking() {
        LogManager.getLogger("MAIN").trace("invoke onMatchMaking")
        user.writeCommand(MatchMakingCommand())
    }

    override fun onPlay(scene: Scene) {
        LogManager.getLogger("MAIN").trace("invoke onPlay")
        user.writeCommand(PlayCommand(scene = scene, playType = "multiplayer"))
        user.writeCommand(PlayersCommand(players = scene.playersMap))
    }

    override fun onSetControlId(value: Byte) {
        LogManager.getLogger("MAIN").trace("invoke onSetControlId")
        val command = ControlCommand(playerId = value)
        user.writeCommand(command)
    }
}