package lab_3.part_1;

public class BearConsumer implements Runnable{
    private HoneyPot pot;
    public BearConsumer(HoneyPot pot) {
        this.pot = pot;
    }
    public void sleepBear(){
        while(pot.getFullPot() == 0) {
            try {
                wait();
                //Thread.currentThread().sleep(2000);
                System.out.println("BEAR sleep.......");
            }catch (InterruptedException e){}
        }
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            //this.sleepBear();
            if(pot.getFullPot() == 1){
                pot.getPotSemaphore().getPermit();
                int tmp = 0;
                while(tmp != -1) {
                    tmp = pot.dequeue();
                }
                pot.setFullPot(0);
                System.out.println(Thread.currentThread().getName() + " ate all honey!");
                pot.getPotSemaphore().getBackPermit();
            }
        }
    }
}
