package server

import commands.ICommand
import io.netty.channel.Channel
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue

abstract class IServerQueue {
    private val queue: ConcurrentLinkedQueue<QueuePair> = ConcurrentLinkedQueue()
    fun size(): Int {
        return queue.size
    }

    fun pool(): QueuePair {
        return queue.poll()
    }

    fun isEmpty(): Boolean {
        return queue.isEmpty()
    }

    fun add(queuePair: QueuePair) {
        queue.offer(queuePair)
    }
    fun add(command: ICommand, channel: Channel) {
        add(QueuePair(command = command.toString(), channel = channel))
    }

    fun add(command: String, channel: Channel) {
        add(QueuePair(command = command, channel = channel))
    }
}