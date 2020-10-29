package rx;

import io.reactivex.rxjava3.core.Observable;

class Main {

    public static void main(String[] args) {
        Observable<String> animals = Observable.just(
                "Tiger", "Elephant", "Cat", "Chameleon", "Frog", "Fish", "Turtle", "Flamingo");

        animals.groupBy(animal -> animal.charAt(0), animal -> animal)
                .concatMapSingle(Observable::toList)
                .subscribe(System.out::println);
    }
}
