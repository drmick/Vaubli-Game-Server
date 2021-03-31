package commands.sc

import commands.ICommand

class MenuCommand(@Transient override val command: String = "menu") : ICommand()