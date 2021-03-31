import io.netty.channel.Channel
import models.User
import java.util.*

object UserService {
    private val users: HashMap<Channel, User> = HashMap()

    fun add(user: User): User? {
        return users.put(user.channel, user)
    }

    fun getUserByChannel(channel: Channel): User? {
        return users[channel]
    }

    fun saveAndExit(user: User) {
        users.remove(user.channel)
    }
}

