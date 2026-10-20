package utils;

public final class Logger {
    private Logger() {}

    public static void log(String fmt, Object... args){

        String message = (args == null || args.length == 0) ?
                fmt :
                String.format(fmt, args);

        Thread current = Thread.currentThread();
        String logFormat = "[%tT.%1$tL][thread:%s] %s%n";
        System.out.printf(logFormat, System.currentTimeMillis(), current.getName(), message);
    }

    private static void logWithLevel(String level, String fmt, Object... args){
        String finalFmt = "[%s] %s".formatted(level.toUpperCase(), fmt);
        log(finalFmt, args);
    }

    public static void info(String fmt, Object... args){
        logWithLevel("INFO", fmt, args);
    }

    public static void error(String fmt, Object... args){
        logWithLevel("ERROR", fmt, args);
    }
}

