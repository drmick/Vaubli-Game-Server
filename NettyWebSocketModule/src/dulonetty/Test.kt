package dulonetty

import dulonetty.tcpsocket.TcpSocketServer
import dulonetty.websocket.WebSocketServer

/**
 * Created by Maks on 29.10.2017.
 */
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val iConnectionListener = TestListener()
        Thread { WebSocketServer(45001, 1, iConnectionListener) }.start()
//        TcpSocketServer(45000, 1, iConnectionListener)

    }
}
