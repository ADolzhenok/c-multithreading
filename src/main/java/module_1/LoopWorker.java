package module_1;

import utils.Logger;
import utils.ThreadSleepUtil;

public final class LoopWorker {
    private String name;
    private volatile Thread innerThread;

    public LoopWorker(String name){
        this.name = name;
    }

    public synchronized void start(){
        if (innerThread != null){
            Logger.log("already started");
            return;
        }
        innerThread = new Thread(() -> {
            int counter = 0;
            while (!Thread.currentThread().isInterrupted()){
                ThreadSleepUtil.safeSleepWithRandomMillis(200,300);
                counter++;
                Logger.log("State in loop: %s", Thread.currentThread().getState());
            }
            Logger.log("counter = %d", counter);
        }, this.name);
        innerThread.start();
    }

    public void stopAsync(){
        if (innerThread != null){
            Logger.log("interrupt");
            innerThread.interrupt();
        }
    }

    public static void main(String[] args){
        LoopWorker loopWorker = new LoopWorker("thread-1");
        loopWorker.start();
        ThreadSleepUtil.safeSleepWithoutThrow(700);
        loopWorker.stopAsync();
    }
}
