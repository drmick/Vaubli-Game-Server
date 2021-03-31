package server

import game.models.Player
import io.netty.channel.Channel

class SessionContext {
    lateinit var channel: Channel
    var player: Player? = null
}