package module_3.utils;

import java.security.SecureRandom;

public final class ThreadSleepUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private ThreadSleepUtil() {}

    public static void safeSleepWithoutThrow(long ms){
        if (ms <= 0) return;

        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public static void safeSleepWithJitter(long ms){
        if (ms <= 0) return;

        try{
            long jitter = RANDOM.nextLong(ms/2);
            if (RANDOM.nextBoolean()) {
                jitter *= -1;
            }
            Thread.sleep(ms + jitter);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
