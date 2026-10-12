package module_2.counter_suite;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter implements Counter {
    private LongAdder count = new LongAdder();

    public void increment() {
        count.increment();
    }

    public void incrementBy(long delta){
        count.add(delta);
    }

    public long getCount(){
        return count.longValue();
    }

}
