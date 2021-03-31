package duloNetty

import com.sun.jdi.event.ExceptionEvent
import io.netty.channel.ChannelHandlerContext


/**
 * Created by Drmick on 03.02.14.
 */
interface IConnectionListener {
    fun onConnect(channelHandlerContext: ChannelHandlerContext)
    fun onDisconnect(channelHandlerContext: ChannelHandlerContext)
    fun onMessage(channelHandlerContext: ChannelHandlerContext, messageEvent: MessageEvent)
    fun onException(channelHandlerContext: ChannelHandlerContext, exceptionEvent: ExceptionEvent)
}
