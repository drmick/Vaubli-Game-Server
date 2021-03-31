package dulonetty.tcpsocket
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

class TcpSocketServer(port: Int, threads: Int, connectionListener: IConnectionListener) {

    internal val SSL = System.getProperty("ssl") != null

    init {
        val sslCtx: SslContext?
        sslCtx = if (SSL) {
            val ssc = SelfSignedCertificate()
            SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build()
        } else {
            null
        }
        val bossGroup = NioEventLoopGroup(threads)
        val workerGroup = NioEventLoopGroup()
        val tcpSocketInitializer = TcpSocketServerInitializer(sslCtx, connectionListener)

        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel::class.java)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(tcpSocketInitializer)

            val ch = b.bind(port).sync().channel()
            ch.closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}