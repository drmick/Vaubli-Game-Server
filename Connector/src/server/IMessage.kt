package server

import models.User

interface IMessage {
    fun run(user: User, message: String)
}
