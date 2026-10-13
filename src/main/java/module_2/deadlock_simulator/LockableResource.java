package module_2.deadlock_simulator;

import java.util.concurrent.locks.ReentrantLock;

public final class LockableResource {
    private final String name;
    private final int id;
    private final ReentrantLock lock = new ReentrantLock();
    private final Object monitor = new Object();

    public LockableResource(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Object getMonitor(){
        return monitor;
    }
    public ReentrantLock getLock() {
        return lock;
    }
}
