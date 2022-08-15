package leetcode.concurrency.threads

import java.util.concurrent.atomic.AtomicInteger

class H3P2O(
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
                obj.notifyAll()
            }
        }
    }

    fun h(releaseHydrogen: Runnable) = launch("h", Companion::isHMax) { run(h, releaseHydrogen) }

    fun o(releaseOxygen: Runnable) = launch("o", Companion::isOMax) { run(o, releaseOxygen) }

    fun p(releaseP: Runnable) = launch("p", Companion::isPMax) { run(p, releaseP) }

    private fun run(counter: AtomicInteger, runnable: Runnable) {
        counter.getAndIncrement()
        runnable.run()
        checkAndRelease(onClear)
    }

    private inline fun launch(name: String, condition: () -> Boolean, command: () -> Unit) {
        synchronized(obj) {
            log("enter $name")
            while (condition.invoke()) {
                log("wait for $name")
                obj.wait()
            }
            log("emit $name")
            command.invoke()
        }
    }
}

private fun log(message: String) {
//    println("${Thread.currentThread().name}: $message ")
}