package dulonetty.tcpsocket

/**
 * Created by Maks on 29.10.2017.
 */
import dulonetty.IConnectionListener
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.ssl.SslContext


class TcpSocketServerInitializer(private val sslCtx: SslContext?, private val connectionListener: IConnectionListener) : ChannelInitializer<SocketChannel>() {

    @Throws(Exception::class)
    public override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()))
        }
        pipeline.addLast("framer", DelimiterBasedFrameDecoder(8192, * Delimiters.lineDelimiter()))
        pipeline.addLast("decoder", StringDecoder())
        pipeline.addLast("encoder", StringEncoder())
        pipeline.addLast(TcpSocketFrameHandler(connectionListener))
        pipeline.addLast(connectionListener)
    }

}