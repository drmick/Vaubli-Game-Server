package game

import game.commands.CommandQueue
import game.commands.list.HubCommandManager
import game.game.PlayerService
import game.game.SessionService
import game.models.Player

class Client {
    fun buildPlayer(nickname: String): Player {
        val player = Player(sessionId = SessionService.nextLongId(),
                            nickName = nickname,
                            type = Player.TYPE.HUMAN,
                            perk = arrayListOf("dictatorship", "freedomOfSpeech", "plannedEconomy").random())
        PlayerService.add(sessionId = player.sessionId, player = player)
        return player
    }

    fun playRandomGame(player: Player) {
        CommandQueue.add(HubCommandManager.PlayRandomGame(player=player))
    }

    fun playerDisconnected(player: Player) {
        CommandQueue.add(HubCommandManager.PlayerDisconnected(player=player))
    }
}