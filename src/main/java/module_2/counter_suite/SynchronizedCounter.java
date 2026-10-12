package module_2.counter_suite;

public class SynchronizedCounter implements Counter {
    private long count = 0;

    public synchronized void increment(){
        count++;
    }

    public synchronized void incrementBy(long delta){
        count += delta;
    }

    public synchronized long getCount(){
        return count;
    }

}
