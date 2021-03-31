package game.game

import game.models.Player
import java.util.*

object PlayerService {
    private val players: HashMap<Long, Player> = HashMap()

    fun add(player: Player, sessionId: Long) {
        players[sessionId] = player
    }
}
