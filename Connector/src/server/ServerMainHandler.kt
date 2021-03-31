package server

import UserService
import commands.HeloCommand
import dulonetty.IConnectionListener
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import org.apache.logging.log4j.LogManager

/**
 * Created by Maks on 20.04.2019.
 */
class ServerMainHandler : IConnectionListener {
    private val logger = LogManager.getLogger("SOCKET")

    override fun onMessage(channelHandlerContext: ChannelHandlerContext, message: String) {
        ServerQueueIn.add(QueuePair(message, channelHandlerContext.channel() ))
    }

    override fun channelInactive(p0: ChannelHandlerContext?) {
    }

    override fun userEventTriggered(p0: ChannelHandlerContext?, evt: Any?) {
        if (evt is WebSocketServerProtocolHandler.HandshakeComplete) {
            ServerService.write(p0!!.channel(), HeloCommand())
        }
    }

    override fun channelWritabilityChanged(p0: ChannelHandlerContext?) {
    }

    override fun channelRead(p0: ChannelHandlerContext?, p1: Any?) {
    }

    override fun channelUnregistered(p0: ChannelHandlerContext?) {
    }

    override fun channelActive(p0: ChannelHandlerContext?) {
        if (p0 != null) {
            ServerService.write(p0.channel(), HeloCommand())
        }
    }

    override fun channelRegistered(p0: ChannelHandlerContext?) {
    }

    override fun channelReadComplete(p0: ChannelHandlerContext?) {
    }

    override fun handlerAdded(p0: ChannelHandlerContext?) {
    }

    override fun exceptionCaught(p0: ChannelHandlerContext?, p1: Throwable?) {
    }

    override fun handlerRemoved(p0: ChannelHandlerContext?) {
        val channel = p0!!.channel()
        val user = UserService.getUserByChannel(channel)
        if (user != null) {
            if (user.player != null) {
                GameClientService.client.playerDisconnected(user.player!!)
            }
        }
    }

}

