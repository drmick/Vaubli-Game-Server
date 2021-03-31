package server

interface IObservable<T> {
    fun addListener(listener: T)

    fun removeListener(listener: T)

}
