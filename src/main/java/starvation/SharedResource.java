package starvation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final Lock lock = new ReentrantLock();

    public void accessResource(String threadName) {
        lock.lock();
        try {
            System.out.println(threadName + " acquired the lock.");
            // Simulate some work with the resource
            Thread.sleep(1000);
            System.out.println(threadName + " released the lock.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
