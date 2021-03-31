package duloNetty
import java.util.*

/**
 * Created by Drmick on 01.02.14.
 */
class NettyServerHandler : SimpleChannelUpstreamHandler(), ChannelHandler {
    var listeners: MutableList<IConnectionListener> = ArrayList()

    @Throws(Exception::class)
    override fun handleUpstream(ctx: ChannelHandlerContext, e: ChannelEvent) {
        super.handleUpstream(ctx, e)
    }

    @Throws(Exception::class)
    override fun channelConnected(channelHandlerContext: ChannelHandlerContext, channelStateEvent: ChannelStateEvent) {
        for (hl in listeners) hl.onConnect(channelHandlerContext)
    }

    @Throws(Exception::class)
    override fun channelClosed(channelHandlerContext: ChannelHandlerContext, channelStateEvent: ChannelStateEvent) {
        for (hl in listeners) hl.onDisconnect(channelHandlerContext)
    }

    override fun messageReceived(channelHandlerContext: ChannelHandlerContext, messageEvent: MessageEvent) {
        for (hl in listeners) hl.onMessage(channelHandlerContext, messageEvent)
    }

    override fun exceptionCaught(channelHandlerContext: ChannelHandlerContext, exceptionEvent: ExceptionEvent) {
        for (hl in listeners) hl.onException(channelHandlerContext, exceptionEvent)
    }
}
