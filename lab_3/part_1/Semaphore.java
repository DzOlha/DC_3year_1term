package lab_3.part_1;

public class Semaphore {
    private int indicator;//0 - busy, 1 - free
    public Semaphore(){
        this.indicator = 1;
    }
    public boolean isFree(){
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
                wait();
            } catch (InterruptedException ex) {}
        }
    }
    public synchronized void getBackPermit(){
        indicator = 1;
        notify();
    }
}
