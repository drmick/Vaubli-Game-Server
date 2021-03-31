package commands.sc

import com.google.gson.annotations.SerializedName
import commands.ICommand

class ControlCommand(@Transient override val command: String = "control",  @SerializedName("PlayerId") val playerId: Byte) : ICommand()