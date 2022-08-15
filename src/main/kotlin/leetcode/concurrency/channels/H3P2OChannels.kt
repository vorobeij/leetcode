package leetcode.concurrency.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import leetcode.concurrency.threads.maxH
import leetcode.concurrency.threads.maxO
import leetcode.concurrency.threads.maxP

class H3P2OChannels {

    val outputFlow = MutableSharedFlow<Boolean>() // marker events to show molecule is ready
    private val channelH = Channel<suspend () -> Unit>()
    private val channelO = Channel<suspend () -> Unit>()
    private val channelP = Channel<suspend () -> Unit>()

    suspend fun collect(molecules: Int) {
        var countMolecules = 0

        while (countMolecules < molecules) {
            countMolecules++
            (1..maxH).forEach { _ -> channelH.receive().invoke() }
            (1..maxO).forEach { _ -> channelO.receive().invoke() }
            (1..maxP).forEach { _ -> channelP.receive().invoke() }
            outputFlow.emit(true)
        }
    }

    suspend fun h(releaseHydrogen: suspend () -> Unit) {
        channelH.send(releaseHydrogen)
    }

    suspend fun o(releaseOxygen: suspend () -> Unit) {
        channelO.send(releaseOxygen)
    }

    suspend fun p(releaseP: suspend () -> Unit) {
        channelP.send(releaseP)
    }
}
