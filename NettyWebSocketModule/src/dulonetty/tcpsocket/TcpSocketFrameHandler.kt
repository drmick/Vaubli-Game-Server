package dulonetty.tcpsocket

import dulonetty.IConnectionListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.apache.logging.log4j.LogManager

/**
 * Echoes uppercase content of text frames.
 */
class TcpSocketFrameHandler(private val connectionListener: IConnectionListener) : SimpleChannelInboundHandler<String>() {
    override fun channelRead0(p0: ChannelHandlerContext?, p1: String?) {
        //logger.debug(p0!!.channel().id().toString(), p1)
        connectionListener.onMessage(p0!!, p1!!)
    }

    companion object {
        private val logger = LogManager.getLogger("TCPSOCKET")
    }
}