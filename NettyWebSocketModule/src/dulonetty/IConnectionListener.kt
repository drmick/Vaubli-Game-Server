package dulonetty

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandler

/**
 * Created by Drmick on 03.02.14.
 */
interface IConnectionListener : ChannelInboundHandler {
    fun onMessage(channelHandlerContext: ChannelHandlerContext, message:String)
}