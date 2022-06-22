package tests

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

fun main() = runBlocking { // this: CoroutineScope
    val flow = flow {
        (1..10).forEach {
            delay(100)
            println(Thread.currentThread().name)
            emit(it)
        }
    }
        //        .flowOn(Dispatchers.IO)
        .flowOn(newSingleThreadContext("test"))

    val lock = ReentrantLock()
    lock.withLock {

    }

    flow.collect { println("$it, ${Thread.currentThread().name}") }


}

// this is your first suspending function
suspend fun doWorld(): Int {
    delay(10000L)
    println("${currentCoroutineContext()[Job]}")
    return 2
}

suspend fun launchParallel(tasks: List<suspend CoroutineScope.() -> String>): List<String> = coroutineScope {
    return@coroutineScope tasks
        .map { async(Dispatchers.IO) { it.invoke(this@coroutineScope) } }
        .map { it.await() }
}