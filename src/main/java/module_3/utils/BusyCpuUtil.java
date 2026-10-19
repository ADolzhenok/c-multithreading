package module_3.utils;

import java.security.SecureRandom;

public final class BusyCpuUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private BusyCpuUtil(){}

    public static void spinOnCpuMillis(long ms){
        long end = System.nanoTime() + ms * 1_000_000L;
        long acc = 0;

        while(System.nanoTime() < end) {
            if (Thread.currentThread().isInterrupted()) {
                throw new RuntimeException("CPU spin interrupted");
            }
            acc += (acc * 31 + 7);
        }
    }

    public static void spinOnCpuMillisWithJitter(long ms){
        if (ms <= 0){
            return;
        }

        long jitter = ms < 2 ? 0 : RANDOM.nextLong(ms / 2);

        if (RANDOM.nextBoolean()){
            jitter *= -1;
        }
        spinOnCpuMillis(ms + jitter);
    }
}
