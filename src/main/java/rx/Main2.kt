package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {

        Observable.fromArray(1, 2, 3)
            .doOnEach { println("before ${Thread.currentThread().name}, $it") }
            .subscribeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { println("${Thread.currentThread().name}, $it") }

        Thread.sleep(1000)
    }
}