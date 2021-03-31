package game.commands

import java.util.concurrent.ConcurrentLinkedQueue

object CommandQueue {
    private val queue: ConcurrentLinkedQueue<ICommand> = ConcurrentLinkedQueue()
    fun size(): Int {
        return queue.size
    }

    fun pool(): ICommand {
        return queue.poll()
    }

    fun add(command: ICommand) {
        queue.add(command)
    }
}