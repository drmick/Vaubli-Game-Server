package server

import commands.ICommand
import io.netty.channel.Channel
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
import org.apache.logging.log4j.LogManager
import java.nio.charset.Charset

internal object ServerService {
    fun write(channel: Channel?, message: String) {
        LogManager.getLogger("SOCKET_SEND_MESSAGE").trace(message)
        // !TODO Сделать нормальный Flush
        if (channel !== null && channel.isActive) {
            val buffer = io.netty.buffer.Unpooled.copiedBuffer(message.toByteArray(Charset.defaultCharset()))
            channel.writeAndFlush(BinaryWebSocketFrame(buffer))
        } else {
            throw Error("Channel empty!")
        }
    }

    fun write(channel: Channel, message: ICommand) {
        write(channel, message.toString())
    }
}