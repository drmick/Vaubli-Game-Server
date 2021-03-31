package dulonetty

import io.netty.channel.ChannelHandlerContext


/**
 * Created by Maks on 29.10.2017.
 */
class TestListener : IConnectionListener {
    override fun onMessage(channelHandlerContext: ChannelHandlerContext, message: String) {
    }

    override fun channelInactive(p0: ChannelHandlerContext?) {
        println("channelInactive") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userEventTriggered(p0: ChannelHandlerContext?, p1: Any?) {
        println("userEventTriggered") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelWritabilityChanged(p0: ChannelHandlerContext?) {
        println("channelWritabilityChanged") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelRead(p0: ChannelHandlerContext?, p1: Any?) {
        println("channelRead") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelUnregistered(p0: ChannelHandlerContext?) {
        println("channelUnregistered") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelActive(p0: ChannelHandlerContext?) {
        println("channelActive") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelRegistered(p0: ChannelHandlerContext?) {
        println("channelRegistered") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channelReadComplete(p0: ChannelHandlerContext?) {
        println("channelReadComplete") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handlerAdded(p0: ChannelHandlerContext?) {
        println("handlerAdded") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exceptionCaught(p0: ChannelHandlerContext?, p1: Throwable?) {
        p1!!.printStackTrace()
        println("exceptionCaught" + p0.toString() + p1.toString()) //To change body of created functions use File | Settings | File Templates.
    }

    override fun handlerRemoved(p0: ChannelHandlerContext?) {
        println("handlerRemoved") //To change body of created functions use File | Settings | File Templates.
    }
}
