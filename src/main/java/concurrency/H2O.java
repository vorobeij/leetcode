package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class H2O {

    private static final AtomicInteger h2o = new AtomicInteger(0);

    public H2O() {

    }

    public static void main(String[] args) {
        Runnable o = () -> System.out.print("O");
        Runnable h = () -> System.out.print("H");
        H2O h2o = new H2O();
        int countMax = 20;
        Runnable oProducer = new Runnable() {
            int counter = 0;

            @Override
            public void run() {
                while (counter < countMax) {
                    try {
                        h2o.hydrogen(h);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    counter++;
                }
            }
        };
        Runnable hProducer = new Runnable() {
            int counter = 0;

            @Override
            public void run() {
                while (counter < countMax) {
                    try {
                        h2o.oxygen(o);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    counter++;
                }
            }
        };
        new Thread(hProducer).start();
        new Thread(oProducer).start();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (h2o) {
            while (h2o.get() == 2) {
                h2o.wait();
            }
            h2o.addAndGet(1);
            releaseHydrogen.run();
            h2o.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (h2o) {
            while (h2o.get() < 0) {
                h2o.wait();
            }
            h2o.addAndGet(-2);
            releaseOxygen.run();
            h2o.notifyAll();
        }
    }
}