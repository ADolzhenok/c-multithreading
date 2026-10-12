package module_2.counter_suite;

public class CounterLoadTesting {

    public static void main(String[] args){
        int threads = 32;
        int itersPerThread = 1_000_000;

        CounterLoad.runLoad(new UnsafeCounter(), threads, itersPerThread);
        CounterLoad.runLoad(new SynchronizedCounter(), threads, itersPerThread);
        CounterLoad.runLoad(new AtomicCounter(), threads, itersPerThread);
        CounterLoad.runLoad(new ReentrantLockCounter(), threads, itersPerThread);
        CounterLoad.runLoad(new LongAdderCounter(), threads, itersPerThread);
    }

}
