package lab_3.part_1;

public class Semaphore {
    private int indicator;//0 - busy, 1 - free
    public Semaphore(){
        this.indicator = 1;
    }
    public synchronized boolean isFree(){
        if(indicator == 1){
            return true;
        }
        else return false;
    }
    public synchronized void getPermit(){
        if(this.isFree()) {
            indicator = 0;
        } else{
            try {
                System.out.println(Thread.currentThread().getName() + " was declined in permit! Waiting....");
                wait();
                getPermit();
            } catch (InterruptedException ex) {}
        }
    }
    public synchronized void getBackPermit(){
        indicator = 1;
        System.out.println(Thread.currentThread().getName() + " return permit!\n");
        try {
            notify();
        }catch (IllegalStateException ex) {
            System.out.println("Error while notifying other bees!");
        }
    }
}
