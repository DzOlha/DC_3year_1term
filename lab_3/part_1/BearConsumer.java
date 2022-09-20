package lab_3.part_1;

public class BearConsumer implements Runnable{
    private HoneyPot pot;
    public BearConsumer(HoneyPot pot) {
        this.pot = pot;
    }
    public void sleepBear(){
        while(pot.getFullPot() == 0) {
            try {
                Thread.currentThread().sleep(1000);
            }catch (InterruptedException e){}
        }
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            this.sleepBear();
            pot.getPotSemaphore().getPermit();
            int tmp = 0;
            while(tmp != -1) {
                tmp = pot.dequeue();
            }
            pot.setFullPot(0);
            pot.getPotSemaphore().getBackPermit();
        }
    }
}
