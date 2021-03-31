package server

class OutputWriter : Runnable {
    override fun run() {
        while (true) {
            if (!ServerQueueOut.isEmpty()) {
                val pair = ServerQueueOut.pool()
                ServerService.write(pair.channel, pair.command)
            }
        }
    }
}