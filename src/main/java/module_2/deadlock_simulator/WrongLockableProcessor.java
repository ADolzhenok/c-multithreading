package module_2.deadlock_simulator;

public class WrongLockableProcessor implements LockableResourceProcessor{
    public void processResources(LockableResource r1, LockableResource r2) throws InterruptedException{
        synchronized (r1.getMonitor()){
            Thread.sleep(100);

            Logger.log("took r1 (id = %d, name = %s)", r1.getId(), r1.getName());
            Logger.log("try to take r2 (id = %d, name = %s)", r2.getId(), r2.getName());
            synchronized (r2.getMonitor()){
                Logger.log("inside the critical section");
            }
        }
        Logger.log("exit");
    }
}
