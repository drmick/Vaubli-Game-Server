package dulonetty.websocket

import dulonetty.IConnectionListener
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.SelfSignedCertificate

/**
 * 30   * A HTTP server which serves Web Socket requests at:
 * 31   *
 * 32   * http://localhost:8080/websocket
 * 33   *
 * 34   * Open your browser at [http://localhost:8080/](http://localhost:8080/), then the demo page will be loaded
 * 35   * and a Web Socket connection will be made automatically.
 * 36   *
 * 37   * This server illustrates support for the different web socket specification versions and will work with:
 * 38   *
 * 39   *
 * 40   *  * Safari 5+ (draft-ietf-hybi-thewebsocketprotocol-00)
 * 41   *  * Chrome 6-13 (draft-ietf-hybi-thewebsocketprotocol-00)
 * 42   *  * Chrome 14+ (draft-ietf-hybi-thewebsocketprotocol-10)
 * 43   *  * Chrome 16+ (RFC 6455 aka draft-ietf-hybi-thewebsocketprotocol-17)
 * 44   *  * Firefox 7+ (draft-ietf-hybi-thewebsocketprotocol-10)
 * 45   *  * Firefox 11+ (RFC 6455 aka draft-ietf-hybi-thewebsocketprotocol-17)
 * 46   *
 * 47
 */
class WebSocketServer(val port: Int, val threads: Int, val connectionListener: IConnectionListener) : Runnable {
    internal val SSL = System.getProperty("ssl") != null
    override fun run() {
        val sslCtx: SslContext?
        sslCtx = if (SSL) {
            val ssc = SelfSignedCertificate()
            SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build()
        } else {
            null
        }

        val bossGroup = NioEventLoopGroup(threads)
        val workerGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .handler(LoggingHandler(LogLevel.INFO))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(WebSocketServerInitializer(sslCtx, connectionListener))

            val ch = b.bind(port).sync().channel()
            ch.closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}