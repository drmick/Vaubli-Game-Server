package server

import dulonetty.IConnectionListener
import io.netty.channel.ChannelHandlerContext
import org.apache.logging.log4j.LogManager

/**
 * Created by Maks on 20.04.2019.
 */
class ServerHandlerLogger : IConnectionListener {
    private val logger = LogManager.getLogger("SOCKET")
    private val receiveLogger = LogManager.getLogger("SOCKET_RECEIVE_MESSAGE")

    override fun onMessage(channelHandlerContext: ChannelHandlerContext, message: String) {
        receiveLogger.trace(message)
    }

    override fun channelInactive(p0: ChannelHandlerContext?) {
        logger.info("channelInactive", p0)
    }

    override fun userEventTriggered(p0: ChannelHandlerContext?, p1: Any?) {
        logger.info("userEventTriggered", p0)
    }

    override fun channelWritabilityChanged(p0: ChannelHandlerContext?) {
        logger.info("channelWritabilityChanged", p0)
    }

    override fun channelRead(p0: ChannelHandlerContext?, p1: Any?) {
        logger.info("channelRead", p0, p1)
    }

    override fun channelUnregistered(p0: ChannelHandlerContext?) {
        logger.info("channelUnregistered", p0)
    }

    override fun channelActive(p0: ChannelHandlerContext?) {
        logger.info("channelActive", p0)
    }

    override fun channelRegistered(p0: ChannelHandlerContext?) {
        logger.info("channelRegistered", p0)
    }

    override fun channelReadComplete(p0: ChannelHandlerContext?) {
        logger.info("channelReadComplete", p0)
    }

    override fun handlerAdded(p0: ChannelHandlerContext?) {
        logger.info("handlerAdded", p0)
    }

    override fun exceptionCaught(p0: ChannelHandlerContext?, p1: Throwable?) {
        logger.info("exceptionCaught", p0, p1)
    }

    override fun handlerRemoved(p0: ChannelHandlerContext?) {
        logger.info("handlerRemoved", p0)
    }

}
