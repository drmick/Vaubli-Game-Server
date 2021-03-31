package dulonetty

import io.netty.channel.ChannelHandlerContext
import java.util.*
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import io.netty.channel.ChannelFuture
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler



/**
 * Created by Drmick on 01.02.14.
 */
class NettyServerHandler : IConnectionListener {
    var listeners: MutableList<IConnectionListener> = ArrayList()

    override fun onMessage(channelHandlerContext: ChannelHandlerContext, message: String) {
        listeners.forEach { it.onMessage(channelHandlerContext, message) }
    }

    override fun channelInactive(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelInactive(p0) }
    }

    override fun userEventTriggered(p0: ChannelHandlerContext?, p1: Any?) {
        listeners.forEach { it.userEventTriggered(p0, p1) }
    }

    override fun channelWritabilityChanged(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelWritabilityChanged(p0) }
    }

    override fun channelRead(p0: ChannelHandlerContext?, p1: Any?) {
        listeners.forEach { it.channelRead(p0, p1) }
    }

    override fun channelUnregistered(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelUnregistered(p0) }
    }

    override fun channelActive(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelActive(p0) }
    }

    override fun channelRegistered(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelRegistered(p0) }
    }

    override fun channelReadComplete(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelReadComplete(p0) }
    }

    override fun handlerAdded(p0: ChannelHandlerContext?) {
        listeners.forEach { it.handlerAdded(p0) }
    }

    override fun exceptionCaught(p0: ChannelHandlerContext?, p1: Throwable?) {
        listeners.forEach { it.exceptionCaught(p0, p1) }
    }

    override fun handlerRemoved(p0: ChannelHandlerContext?) {
        listeners.forEach { it.handlerRemoved(p0) }
    }
}