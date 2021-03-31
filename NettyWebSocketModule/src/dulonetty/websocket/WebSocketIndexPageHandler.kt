package dulonetty.websocket

import io.netty.channel.ChannelHandler

/**
 * Created by Maks on ...
 */
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPipeline
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.FullHttpResponse
import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpUtil
import io.netty.handler.ssl.SslHandler
import io.netty.util.CharsetUtil

import io.netty.handler.codec.http.HttpMethod.GET
import io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST
import io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN
import io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND
import io.netty.handler.codec.http.HttpResponseStatus.OK
import io.netty.handler.codec.http.HttpVersion.HTTP_1_1

/**
 * Outputs index page content.
 */
class WebSocketIndexPageHandler(private val websocketPath: String) : SimpleChannelInboundHandler<FullHttpRequest>(), ChannelHandler {

    @Throws(Exception::class)
    override fun channelRead0(ctx: ChannelHandlerContext, req: FullHttpRequest) {
        //println("11111111111111111111")
        // Handle a bad request.

        if (!req.decoderResult().isSuccess) {
            sendHttpResponse(ctx, req, DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST))
            return
        }

        // Allow only GET methods.
        if (req.method() !== GET) {
            sendHttpResponse(ctx, req, DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN))
            return
        }

        // Send the index page
        if ("/debug" == req.uri()) {
            val webSocketLocation = getWebSocketLocation(ctx.pipeline(), req, websocketPath)
            val content = Unpooled.copiedBuffer(WebSocketServerIndexPage.getContent(webSocketLocation).toByteArray())
            val res = DefaultFullHttpResponse(HTTP_1_1, OK, content)

            res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-")
            HttpUtil.setContentLength(res, content.readableBytes().toLong())

            sendHttpResponse(ctx, req, res)
        } else {
            sendHttpResponse(ctx, req, DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND))
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }

    private fun sendHttpResponse(ctx: ChannelHandlerContext, req: FullHttpRequest, res: FullHttpResponse) {
        // Generate an error page if response getStatus code is not OK ().
        if (res.status().code() != 200) {
            val buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8)
            res.content().writeBytes(buf)
            buf.release()
            HttpUtil.setContentLength(res, res.content().readableBytes().toLong())
        }

        // Send the response and close the connection if necessary.
        val f = ctx.channel().writeAndFlush(res)
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE)
        }
    }

    private fun getWebSocketLocation(cp: ChannelPipeline, req: HttpRequest, path: String): String {
        var protocol = "ws"
        if (cp.get(SslHandler::class.java) != null) {
            // SSL in use so use Secure WebSockets
            protocol = "wss"
        }
        return protocol + "://" + req.headers().get(HttpHeaderNames.HOST) + path
    }
}