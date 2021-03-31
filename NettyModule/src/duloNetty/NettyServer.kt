package duloNetty

import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import java.net.InetSocketAddress
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by Drmick on 01.02.14.
 */
class NettyServer @Throws(UnknownHostException::class)
constructor(port: Int, eventHandler: IConnectionListener) : IConnectionListener {

    internal var channels: MutableMap<Int, Channel> = HashMap()
    val bootstrap: ServerBootstrap

    init {
        val factory = NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
        bootstrap = ServerBootstrap(factory)
        bootstrap.pipelineFactory = NettyTcpPipelineFactory(this, eventHandler)
        bootstrap.setOption("child.tcpNoDelay", true)
        bootstrap.setOption("child.keepAlive", true)
        bootstrap.bind(InetSocketAddress(port))


//        Log.debug("Server started on port $port")
    }

    override fun onConnect(channelHandlerContext: ChannelHandlerContext) {
        val channel = channelHandlerContext.channel
        channel.config.connectTimeoutMillis = 30000
        channels.put(channel.id, channel)
//        Log.debug("Client connected! ChannelId: ${channel.id}, Channel: ${channel.remoteAddress}")
    }

    override fun onDisconnect(channelHandlerContext: ChannelHandlerContext) {
        val channel = channelHandlerContext.channel
        channels.remove(channel.id)
//        Log.debug("Client disconnected! ChannelId: ${channel.id}, Channel: ${channel.remoteAddress}")
    }

    override fun onMessage(channelHandlerContext: ChannelHandlerContext, messageEvent: MessageEvent) {
        val channel = channelHandlerContext.channel
        val message = messageEvent.message.toString()
     //   Log.debug("Received message! ChannelId: ${channel.id}, Channel: ${channel.remoteAddress}, message: $message")
    }

    override fun onException(channelHandlerContext: ChannelHandlerContext, exceptionEvent: ExceptionEvent) {
       // Log.debug("Socket exception: ${exceptionEvent.cause.message}")
        channels.remove(channelHandlerContext.channel.id)
        channelHandlerContext.channel.close()
    }
}
