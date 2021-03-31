package game

import game.commands.CommandQueue
import game.commands.ICommand
import game.services.HubService
import org.apache.logging.log4j.LogManager

class GameEngine : Runnable {

    private fun processCommand() {
        while (CommandQueue.size() > 0) {
            val command: ICommand = CommandQueue.pool()
            invokeCommand(command)
        }
    }

    fun invokeCommand(command: ICommand) {
        LogManager.getLogger("MAIN").trace(command.toString())
        command.execute()
    }

    override fun run() {
        var previous = System.currentTimeMillis()
        var lag = 0.0;
        while (true) {
            val current = System.currentTimeMillis();
            val elapsed = current - previous;
            previous = current;
            lag += elapsed

            processCommand()
            HubService.process(this, current)
        }
    }


}