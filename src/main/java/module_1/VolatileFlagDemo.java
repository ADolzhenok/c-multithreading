package module_1;

import utils.ThreadSleepUtil;

public final class VolatileFlagDemo {
    private volatile boolean running;

    public void start(){
        running = true;
        Thread thread = new Thread(() -> {
            while(running){
            }
        });
        thread.start();
    }

    public void stop(){
        running = false;
    }

    public static void main(String[] args){
        VolatileFlagDemo demo = new VolatileFlagDemo();
        demo.start();
        ThreadSleepUtil.safeSleepWithoutThrow(1000);
        demo.stop();
    }
}
