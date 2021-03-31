package server

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import router.Router


class InputReader : Runnable {
    override fun run() {
        while (true) {
            if (!ServerQueueIn.isEmpty()) {
                val pair = ServerQueueIn.pool()
                GlobalScope.launch {
                    Router.executeRoute(channel = pair.channel, message = pair.command)
                }
            }
        }
    }
}
