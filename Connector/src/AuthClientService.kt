
import client.BaseClient

object AuthClientService {
    val client = BaseClient(executor = Config.INSTANCE_NAME)
}