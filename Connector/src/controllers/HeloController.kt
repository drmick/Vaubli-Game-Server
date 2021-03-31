package controllers

import server.IMessage
import commands.sc.MenuCommand
import models.User
import server.ServerService

class HeloController : IMessage {
    override fun run(user: User, message: String) {
        ServerService.write(user.channel, MenuCommand().toString())
    }
}
