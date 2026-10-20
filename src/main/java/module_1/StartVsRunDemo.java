package module_1;

import utils.Logger;
import utils.ThreadSleepUtil;

import java.util.ArrayList;
import java.util.List;

public final class StartVsRunDemo {
    private StartVsRunDemo(){}

    private static class MyThread extends Thread {
        public MyThread(String name){
            super(name);
        }

        public void run(){
            Logger.log("run() called from MyThread");
            ThreadSleepUtil.safeSleepWithRandomMillis(100, 800);
        }
    }

    private static class MyRunnable implements Runnable{
        public void run(){
            Logger.log("run() called from MyRunnable");
            ThreadSleepUtil.safeSleepWithRandomMillis(100, 800);
        }
    }

    public static void main(String[] args){
        MyThread thread1 = new MyThread("thread-1");
        Thread thread2 = new Thread(new MyRunnable(), "thread-2");
        Thread thread3 = new Thread(() -> {
            Logger.log("run() called from Lambda");
            ThreadSleepUtil.safeSleepWithRandomMillis(100, 800);
        }, "thread-3");

        List<Thread> threads = new ArrayList<>(List.of(thread1, thread2, thread3));

//        for (Thread thread: threads){
//            thread.run();
//        }

        threads.forEach(thread -> Logger.log("State of %s: %s", thread.getName(), thread.getState()));

        for (Thread thread: threads){
            thread.start();
            Logger.log("State of %s: %s", thread.getName(), thread.getState());
        }

        ThreadSleepUtil.safeSleepWithoutThrow(200);

        threads.forEach(thread -> Logger.log("State of %s: %s", thread.getName(), thread.getState()));

        for (Thread thread: threads){
            try {
                thread.join();
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }

        threads.forEach(thread -> Logger.log("State of %s: %s", thread.getName(), thread.getState()));

    }



}
