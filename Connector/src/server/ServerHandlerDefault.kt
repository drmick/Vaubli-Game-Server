package server

import dulonetty.IConnectionListener
import io.netty.channel.ChannelHandlerContext

/**
 * Created by Drmick on 01.02.14.
 */
class ServerHandlerDefault : IConnectionListener, IObservable<IConnectionListener> {
    private val listeners = mutableListOf<IConnectionListener>()

    override fun addListener(listener: IConnectionListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: IConnectionListener) {
        listeners.remove(listener)
    }

    override fun onMessage(channelHandlerContext: ChannelHandlerContext, message: String) {
        listeners.forEach { it.onMessage(channelHandlerContext, message) }
    }

    override fun channelInactive(p0: ChannelHandlerContext?) {
        listeners.forEach { it.channelInactive(p0) }
    }

    override fun userEventTriggered(p0: ChannelHandlerContext?, evt: Any?) {
        listeners.forEach { it.userEventTriggered(p0, evt) }
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

    override fun channelActive(p0: ChannelHandlerContext) {
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

