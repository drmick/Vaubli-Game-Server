package controllers

import server.IMessage
import game.models.Player
import listeners.GamePlayerListener
import models.User

class PlayController : IMessage {
    override fun run(user: User, message: String) {
//        val request = Gson().fromJson(message, PlayCommand::class.java)
        if (user.player == null) {
            user.playerListener = GamePlayerListener(user)
            val player: Player = GameClientService.client.buildPlayer(nickname = user.nickName)
            user.player = player
            player.addListener(user.playerListener)
            GameClientService.client.playRandomGame(player)
        }
    }
}