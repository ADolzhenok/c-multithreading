package module_2.deadlock_simulator;

public final class Logger {
    private Logger() {}

    public static void log(String fmt, Object... args) {
        String msg = (args == null || args.length == 0) ?
                fmt :
                String.format(fmt, args);

        System.out.printf("[%tT.%1$tL][thread:%s] %s%n",
                System.currentTimeMillis(),
                Thread.currentThread().getName(),
                msg);
    }


}
