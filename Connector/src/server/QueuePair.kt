package server

import io.netty.channel.Channel

class QueuePair(val command: String, val channel: Channel)