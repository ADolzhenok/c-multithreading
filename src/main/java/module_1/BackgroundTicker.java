package module_1;

import utils.Logger;
import utils.ThreadSleepUtil;

public final class BackgroundTicker {
    private BackgroundTicker(){}

    private static Thread startDaemon(String name, int periodMillis){
        Thread thread = new Thread(() -> {
            while (true){
                ThreadSleepUtil.safeSleepWithoutThrow(periodMillis);
                Logger.log("Tick");
            }
        }, name);
        thread.setDaemon(true);
        return thread;
    }

    public static void main(String[] args){
        BackgroundTicker.startDaemon("ticker", 200).start();

        new Thread(() -> {
            ThreadSleepUtil.safeSleepWithoutThrow(1000);
            Logger.log("finished work");
        }).start();

        new Thread(() -> {
            ThreadSleepUtil.safeSleepWithoutThrow(2000);
            Logger.log("finished work");
        }).start();
    }


}
