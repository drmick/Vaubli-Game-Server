package duloNetty
import io.netty.channel.ChannelPipeline
import org.jboss.netty.channel.ChannelPipeline
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder
import org.jboss.netty.handler.codec.frame.Delimiters
import org.jboss.netty.handler.codec.string.StringDecoder
import org.jboss.netty.handler.codec.string.StringEncoder
import java.nio.channels.Channels

/**
 * Created by Drmick on 01.02.14.
 */
class NettyTcpPipelineFactory(var currentEventHandler: IConnectionListener, var externalEventHandler: IConnectionListener) : ChannelPipelineFactory {

    @Throws(Exception::class)
    override fun getPipeline(): ChannelPipeline {
        val pipeline = Channels.pipeline()
        val nettyServerHandler = NettyServerHandler()

        nettyServerHandler.listeners.add(currentEventHandler)
        nettyServerHandler.listeners.add(externalEventHandler)

        pipeline.addLast("framer", DelimiterBasedFrameDecoder(8192, * Delimiters.lineDelimiter()))
        pipeline.addLast("decoder", StringDecoder())
        pipeline.addLast("encoder", StringEncoder())
        pipeline.addLast("handler", nettyServerHandler)
        return pipeline
    }
}
