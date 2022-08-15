package leetcode.concurrency.coroutines

import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.delay

class H3P2OActor(
    private var onClear: (() -> Unit)? = null
) {

    companion object {
        private val h = AtomicInteger(0)
        private val o = AtomicInteger(0)
        private val p = AtomicInteger(0)
        private val obj = Object()
        private fun isOMax(): Boolean = o.get() == 1
        private fun isHMax(): Boolean = h.get() == 3
        private fun isPMax(): Boolean = p.get() == 2

        @Synchronized
        private fun checkAndRelease(onClear: (() -> Unit)? = null) {
            if (isOMax() && isHMax() && isPMax()) {
                h.set(0)
                o.set(0)
                p.set(0)
                onClear?.invoke()
                log("notify all")
//                obj.notifyAll()
            }
        }
    }

    suspend fun h(releaseHydrogen: Runnable) = launch("h", Companion::isHMax) { run(h, releaseHydrogen) }

    suspend fun o(releaseOxygen: Runnable) = launch("o", Companion::isOMax) { run(o, releaseOxygen) }

    suspend fun p(releaseP: Runnable) = launch("p", Companion::isPMax) { run(p, releaseP) }

    private fun run(counter: AtomicInteger, runnable: Runnable) {
        counter.getAndIncrement()
        runnable.run()
        checkAndRelease(onClear)
    }

    private suspend fun launch(name: String, condition: () -> Boolean, command: () -> Unit) {
        log("enter $name")
        while (condition.invoke()) {
            log("wait for $name")
            delay(1)
        }
        log("emit $name")
        command.invoke()
    }
}

private fun log(message: String) {
//    println("${Thread.currentThread().name}: $message ")
}
