package game.commands.list

import game.commands.ICommand
import game.models.scene.Scene
import org.apache.logging.log4j.LogManager

class Scenes {
    class Create(name: String): ICommand {
        override fun execute() {
        }
    }

    class List: ICommand {
        override fun execute() {
            println("execute list")
        }
    }

    class Play(val scene: Scene): ICommand {
        override fun execute() {
            LogManager.getLogger("MAIN").trace("execute Scene.Play")
            scene.status = Scene.STATUS.PLAY
        }
    }
}