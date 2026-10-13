package module_2.deadlock_simulator;

public class DeadlockSimulatorDemo {
    public static void main(String[] args){
        final int iterations = 1000;
        final long pauseBetweenLocksMs = 100;

        LockableResource rA = new LockableResource(1, "A");
        LockableResource rB = new LockableResource(2, "B");

        for (int i = 1; i <= iterations; i++){
            //WrongLockableProcessor processor = new WrongLockableProcessor();
            //OrderedLockableResourceProcessor processor = new OrderedLockableResourceProcessor();
            TryLockLockableResourceProcessor processor = new TryLockLockableResourceProcessor();
            Thread thread1 = new Thread(() -> {
                try {
                    processor.processResources(rA, rB);
                }
                catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            });
            Thread thread2 = new Thread(() -> {
                try {
                    processor.processResources(rB, rA);
                }
                catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            });
            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }

        }
    }
}
