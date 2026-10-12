package module_2.counter_suite;

public class UnsafeCounter implements Counter {
    private  long count = 0;

    public void increment(){
        count++;
    }

    public void incrementBy(long delta) {
        count += delta;
    }

    public long getCount() {
        return count;
    }

}
