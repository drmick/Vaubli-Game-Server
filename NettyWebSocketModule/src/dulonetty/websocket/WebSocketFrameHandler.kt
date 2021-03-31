package dulonetty.websocket

import dulonetty.IConnectionListener

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import io.netty.handler.codec.http.websocketx.WebSocketFrame
import org.apache.logging.log4j.LogManager
import java.util.zip.Inflater
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.protobuf.ProtobufDecoder
import java.nio.charset.Charset
import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream
import java.io.ObjectInputStream
import io.netty.util.ReferenceCountUtil


/**
 * Echoes uppercase content of text frames.
 */
class WebSocketFrameHandler(private val connectionListener: IConnectionListener) : SimpleChannelInboundHandler<WebSocketFrame>() {

    @Throws(Exception::class)
    override fun channelRead0(context: ChannelHandlerContext, frame: WebSocketFrame) {
        //println(frame)
        when (frame) {
            is TextWebSocketFrame -> {
                val text: String = frame.text()
                //logger.info(text)
                connectionListener.onMessage(context, text)
            }
            is BinaryWebSocketFrame -> {
                val text = frame.content().toString(Charset.forName("UTF-8"))
                //logger.info(text)
                connectionListener.onMessage(context, text)
            }
            is HttpRequest ->{
                //println(1)
            }

            else -> {

                val message = "unsupported frame type: " + frame.javaClass.name
                logger.warn(message)
                throw UnsupportedOperationException(message)
            }
        }
    }

    private fun decodeByteBuff1(buf: ByteBuf): String {
        val temp = ByteArray(buf.readableBytes())
        val bis = ByteBufInputStream(buf)
        bis.read(temp)
        bis.close()
        val decompresser = Inflater(true)
        decompresser.setInput(temp, 0, temp.size)
        val sb = StringBuilder()
        val result = ByteArray(1024)
        while (!decompresser.finished()) {
            val resultLength = decompresser.inflate(result)
            sb.append(String(result, 0, resultLength, Charset.forName("UTF-8")))
        }
        decompresser.end()
        return sb.toString()
    }

    fun fromBytes(buf: ByteBuf) {
        val len = buf.readInt()
        val compressedBody = ByteArray(len)

        for (i in 0 until len) {
            compressedBody[i] = buf.readByte()
        }

        try {
            val obj = ObjectInputStream(GZIPInputStream(ByteArrayInputStream(compressedBody)))
            val values = obj.readObject() as Map<String, Any>
            obj.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun decodeByteBuff(buf: ByteBuf): String {
        val json: String
        try {
            val bytes = ByteArray(buf.readableBytes())
            buf.readBytes(bytes)
            json = String(bytes)
        } finally {
            ReferenceCountUtil.release(buf)
        }
        return json
    }


    companion object {
        private val logger = LogManager.getRootLogger()
    }
}