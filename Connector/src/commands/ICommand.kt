package commands

import com.google.gson.Gson

abstract class ICommand {
    abstract val command: String

    override fun toString(): String {
        return this.command + "|" + Gson().toJson(this).toString()
    }
}