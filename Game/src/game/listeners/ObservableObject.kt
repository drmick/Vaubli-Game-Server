package game.listeners

import java.util.*

open class ObservableObject<T> {
    val listeners: MutableSet<T> = HashSet()

    fun addListener(listener:T) {
        listeners.add(listener)
    }

    fun removeListener(listener:T) {
        listeners.remove(listener)
    }
}