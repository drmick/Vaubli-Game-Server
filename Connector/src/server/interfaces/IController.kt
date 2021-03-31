package server.interfaces

import server.SessionContext

/**
 * Created by Drmick on 16.02.14.
 */
interface IController {
    fun run(message: String, sessionContext: SessionContext)
    fun hasAuth():Boolean {
        return false
    }
}