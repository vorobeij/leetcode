package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class H3P2O {

    private static final AtomicInteger h = new AtomicInteger(0);
    private static final AtomicInteger o = new AtomicInteger(0);
    private static final AtomicInteger p = new AtomicInteger(0);
    private static final Object obj = new Object();

    public H3P2O() {

    }

    private static boolean isOMax() {
        return o.get() == 1;
    }

    private static boolean isHMax() {
        return h.get() == 3;
    }

    private static boolean isPMax() {
        return p.get() == 2;
    }

    private static synchronized void checkAndRelease() {
        if (isOMax() && isHMax() && isPMax()) {
            h.set(0);
            o.set(0);
            p.set(0);
            System.out.print("-");
            obj.notifyAll();
        }
    }

    public void h(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (obj) {
            while (isHMax()) {
                obj.wait();
            }
            run(h, releaseHydrogen);
        }
    }

    private void run(AtomicInteger counter, Runnable runnable) {
        counter.getAndIncrement();
        runnable.run();
        checkAndRelease();
    }

    public void o(Runnable releaseOxygen) throws InterruptedException {
        synchronized (obj) {
            while (isOMax()) {
                obj.wait();
            }
            run(o, releaseOxygen);
        }
    }

    public void p(Runnable releaseP) throws InterruptedException {
        synchronized (obj) {
            while (isPMax()) {
                obj.wait();
            }
            run(p, releaseP);
        }
    }
}