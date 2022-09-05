package leetcode.rx

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    println("0: ${Thread.currentThread().id}")

    Single.just("Some string") // строка 1
        .map { str -> str.length } // строка 2
        .subscribeOn(Schedulers.io()) // строка 3
        .map { length -> 42 * length } // строка 4
        .subscribe { number -> println(Thread.currentThread().id) } // строка 5

    Thread.sleep(1000)
}
