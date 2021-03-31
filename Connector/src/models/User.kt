package models

import commands.ICommand
import game.models.Player
import io.netty.channel.Channel
import listeners.GamePlayerListener
import server.ServerService


class User(val sessionId: Long, val nickName: String, val channel: Channel) {
    lateinit var playerListener: GamePlayerListener
    var player: Player? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (sessionId != other.sessionId) return false

        return true
    }

    override fun hashCode(): Int {
        return sessionId.hashCode()
    }

    fun writeCommand(command: ICommand) {
        ServerService.write(channel, command)
    }
}
