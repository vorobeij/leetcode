package coroutines

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

sealed class CounterMsg
class CounterIncrement(val increment: Int = 1) : CounterMsg()
class GetResult(val response: CompletableDeferred<Int>) : CounterMsg()

@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0
    for (msg in channel) {
        when (msg) {
            is CounterIncrement -> counter += msg.increment
            is GetResult -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking {
    val counter = counterActor()

    withContext(Dispatchers.IO) {
        counter.send(CounterIncrement())
    }
    val response = CompletableDeferred<Int>()
    counter.send(GetResult(response))
    println(response.await())
    counter.close()
}
