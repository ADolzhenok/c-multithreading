package module_2.deadlock_simulator;

import java.util.concurrent.TimeUnit;

public class TryLockLockableResourceProcessor implements LockableResourceProcessor{
    public void processResources(LockableResource r1, LockableResource r2) throws InterruptedException{
        int delay = 10;
        boolean success = false;

        while(!success){
            Logger.log("try to lock ", r1.getId());
            if(!r1.getLock().tryLock(50, TimeUnit.MILLISECONDS)){
                Logger.log("failed to take " + r1.getId());
                delay = Math.min(delay*2, 1000);
                Thread.sleep(delay);
                continue;
            }
            delay = 10;
            Logger.log("took " + r1.getId());
            Logger.log("try to take " + r2.getId());
            if (!r2.getLock().tryLock(50, TimeUnit.MILLISECONDS)){
                Logger.log("failed to take " + r2.getId());
                r1.getLock().unlock();
                delay = Math.min(delay*2, 1000);
                Thread.sleep(delay);
                continue;
            }
            else {
                success = true;
            }

            try {
                Logger.log("inside the critical section");
            }
            finally {
                if(r1.getLock().isHeldByCurrentThread()){
                    r1.getLock().unlock();
                }
                if(r2.getLock().isHeldByCurrentThread()){
                    r2.getLock().unlock();
                }
            }
            Logger.log("exit");
        }
    }
}
