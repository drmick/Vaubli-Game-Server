package commands.sc

import commands.ICommand


class MatchMakingCommand(@Transient override val command: String = "matchmaking") : ICommand()