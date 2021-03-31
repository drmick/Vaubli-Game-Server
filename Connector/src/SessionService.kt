import java.util.concurrent.atomic.AtomicLong

object SessionService {
    private val sequence: AtomicLong = AtomicLong(0);
    fun nextLongId(): Long {
        return sequence.incrementAndGet()
    }
}
