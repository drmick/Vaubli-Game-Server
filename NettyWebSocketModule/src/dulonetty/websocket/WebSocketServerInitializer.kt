package dulonetty.websocket

import dulonetty.IConnectionListener
import io.netty.channel.*
import io.netty.handler.ssl.SslContext

/**
 * Created by Maks on 29.10.2017.
 */
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.codec.http.HttpResponseEncoder
import io.netty.handler.codec.http.HttpRequestDecoder




/**
 * 28
 */
class WebSocketServerInitializer(private val sslCtx: SslContext?, private val connectionListener: IConnectionListener) : ChannelInitializer<SocketChannel>() {

    @Throws(Exception::class)
    public override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()))
        }
        //pipeline.addLast(HttpServerHander())
        pipeline.addLast(HttpServerCodec())
        pipeline.addLast(HttpObjectAggregator(65536))
        pipeline.addLast(WebSocketServerCompressionHandler())
        pipeline.addLast(WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true))
        pipeline.addLast(WebSocketIndexPageHandler(WEBSOCKET_PATH))
        pipeline.addLast(WebSocketFrameHandler(connectionListener))
        // pipeline.addLast(HttpServerFrameHander(connectionListener))

        pipeline.addLast(connectionListener)
    }

    companion object {
        private val WEBSOCKET_PATH = "/"
    }


}
