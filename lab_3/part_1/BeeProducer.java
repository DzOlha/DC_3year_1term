package lab_3.part_1;

public class BeeProducer implements Runnable{
    private HoneyPot pot;
    public BeeProducer(HoneyPot p) {
        this.pot = p;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            if(pot.getFullPot() == 1){
                try {
                    wait();
                }catch (InterruptedException e){}
            }
            pot.getPotSemaphore().getPermit();
            pot.enqueue(1);
            if(pot.isFull()) {
                pot.setFullPot(1);
            }
            pot.getPotSemaphore().getBackPermit();
        }
    }
}
