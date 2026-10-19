package module_3.utils;

public final class MeasurementUtils {
    private MeasurementUtils() {}

    public static void measure(String taskName, Runnable task){
        long startNanos = System.nanoTime();
        try{
            task.run();
        }
        catch (Exception ex) {
            Logger.info("Exception in task-name: %s message", taskName, ex.getMessage());
            throw ex;
        }
        finally {
            long endNanos = System.nanoTime();
            long timeElapsedMs = (endNanos - startNanos) / 1_000_000;
            Logger.info("Task-name: %s Time = %sms", taskName, timeElapsedMs);
        }
    }
}
