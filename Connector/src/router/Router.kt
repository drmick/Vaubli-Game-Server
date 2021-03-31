package router

import server.IMessage
import controllers.HeloController
import controllers.PlayController
import io.netty.channel.Channel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import models.User
import org.apache.logging.log4j.LogManager
import java.util.*

object Router {
    private var runnableMap: MutableMap<String, IMessage> = HashMap()

    init {
        runnableMap["helo"] = HeloController()
        runnableMap["play"] = PlayController()
    }

    suspend fun executeRoute(channel: Channel, message: String): Any = try {
        val splittedMessage = message.split("|")
        val code = splittedMessage[0]
        val textCommand = splittedMessage[1]
        var user = UserService.getUserByChannel(channel = channel)
        if (user == null) {
            val result = GlobalScope.async { AuthClientService.client.login(address = channel.remoteAddress().toString()) }.await()
            user = User(channel = channel, nickName = result.nickname, sessionId = SessionService.nextLongId())
            UserService.add(user)
        }
        val controller = runnableMap[code] ?: throw Exception("Controller $code not found")
        controller.run(user, textCommand)

    } catch (e:Exception) {
        LogManager.getLogger("MAIN").error(e)
    }
}
