package module_2.deadlock_simulator;

public interface LockableResourceProcessor {
    void processResources(LockableResource r1, LockableResource r2) throws InterruptedException;
}
