package module_2.counter_suite;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {
    private final ReentrantLock lock = new ReentrantLock(false);
    private long count = 0;

    public void increment(){
        lock.lock();
        try {
            count++;
        }
        finally {
            lock.unlock();
        }
    }

    public void incrementBy(long delta){
        lock.lock();
        try {
            count += delta;
        }
        finally {
            lock.unlock();
        }
    }

    public long getCount(){
        lock.lock();
        try {
            return count;
        }
        finally {
            lock.unlock();
        }
    }

}
