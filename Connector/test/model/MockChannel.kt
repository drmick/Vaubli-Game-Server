package model

import io.netty.buffer.ByteBufAllocator
import io.netty.channel.*
import io.netty.util.Attribute
import io.netty.util.AttributeKey
import java.net.SocketAddress

/**
 * Created by Maks on 20.04.2019.
 */
class MockChannel: Channel {
    override fun writeAndFlush(p0: Any?, p1: ChannelPromise?): ChannelFuture? {
        println("Send message $p0")
        return null
    }

    override fun writeAndFlush(p0: Any?): ChannelFuture? {
        println("Send message $p0")
        return null
    }

    override fun isActive(): Boolean {
        return false
    }

    override fun alloc(): ByteBufAllocator {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun id(): ChannelId {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newPromise(): ChannelPromise {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun write(p0: Any?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(p0: Any?, p1: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeFuture(): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> hasAttr(p0: AttributeKey<T>?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun flush(): Channel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(p0: SocketAddress?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(p0: SocketAddress?, p1: SocketAddress?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(p0: SocketAddress?, p1: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(p0: SocketAddress?, p1: SocketAddress?, p2: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newFailedFuture(p0: Throwable?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remoteAddress(): SocketAddress {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun config(): ChannelConfig {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newSucceededFuture(): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isOpen(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bytesBeforeUnwritable(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bytesBeforeWritable(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pipeline(): ChannelPipeline {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(p0: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(): Channel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun voidPromise(): ChannelPromise {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parent(): Channel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> attr(p0: AttributeKey<T>?): Attribute<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deregister(): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deregister(p0: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compareTo(other: Channel?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disconnect(): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disconnect(p0: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsafe(): Channel.Unsafe {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newProgressivePromise(): ChannelProgressivePromise {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWritable(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun metadata(): ChannelMetadata {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun localAddress(): SocketAddress {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bind(p0: SocketAddress?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bind(p0: SocketAddress?, p1: ChannelPromise?): ChannelFuture {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isRegistered(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun eventLoop(): EventLoop {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
