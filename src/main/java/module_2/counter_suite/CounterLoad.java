package module_2.counter_suite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public final class CounterLoad {

    public static void runLoad(Counter counter, int threadsCount, int iterationsPerThread){
        List<Thread> threadList = new ArrayList<>(threadsCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch readyLatch = new CountDownLatch(threadsCount);

        for (int i = 0; i < threadsCount; i++){
            Thread thread = new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                    for (int iter = 0; iter < iterationsPerThread; iter++) {
                        counter.increment();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threadList.add(thread);
            thread.start();
        }

        try{
            readyLatch.await();
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        long start = System.nanoTime();
        startLatch.countDown();
        for (Thread thread : threadList){
            try {
                thread.join();
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        long end = System.nanoTime();

        System.out.printf(
                "%s: expected=%d, actual=%d, timeMs=%d\n",
                counter.getClass().getSimpleName(),
                threadsCount * iterationsPerThread,
                counter.getCount(),
                (end - start) / 1_000_000);


    }

}
