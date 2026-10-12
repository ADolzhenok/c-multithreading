package module_2.counter_suite;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {
    private final AtomicLong count = new AtomicLong(0);

    public void increment(){
        count.incrementAndGet();
    }

    public void incrementBy(long delta){
        count.addAndGet(delta);
    }

    public long getCount(){
        return count.get();
    }


}
