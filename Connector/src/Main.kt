import dulonetty.websocket.WebSocketServer
import game.GameEngine
import org.apache.logging.log4j.LogManager
import server.*


@Throws(Exception::class)
fun main(args: Array<String>) {
    val serverHandler = ServerHandlerDefault()

    serverHandler.addListener(ServerHandlerLogger())
    serverHandler.addListener(ServerMainHandler())
    val gameEngineThread = Thread (GameEngine(), "Game engine service")
    val outputWriter = Thread (OutputWriter(), "Socket writer")
    val inputReader = Thread (InputReader(), "Socket reader service")
    val webSocketServer = Thread (WebSocketServer(45001, 1, serverHandler), "WebSocket server")

    gameEngineThread.start()
    LogManager.getLogger("MAIN").info("${gameEngineThread.name} - ${gameEngineThread.state}")
    outputWriter.start()
    LogManager.getLogger("MAIN").info("${outputWriter.name} - ${outputWriter.state}")
    inputReader.start()
    LogManager.getLogger("MAIN").info("${inputReader.name} - ${inputReader.state}")
    webSocketServer.start()
    LogManager.getLogger("MAIN").info("${webSocketServer.name} - ${webSocketServer.state}")
}