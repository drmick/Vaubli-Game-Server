package commands.sc

import com.google.gson.annotations.SerializedName
import commands.ICommand

class PlayersCommand(
    @Transient override val command: String = "players",
    @Transient val players: Array<game.models.Player?>,
) : ICommand() {

    @SerializedName("List")
    var list: MutableSet<Player> = HashSet()

    init {
        for (i in players.indices) {
            val player = players[i]
            if (player != null) {
                list.add(Player(id=i, name = player.nickName, player.perk))
            }
        }

    }


    class Player(
        @SerializedName("Id") val id: Int,
        @SerializedName("Name") var name: String,
        @SerializedName("Perk") var perk: String
    )

}
