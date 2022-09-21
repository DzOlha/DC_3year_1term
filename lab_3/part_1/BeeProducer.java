package lab_3.part_1;

public class BeeProducer implements Runnable{
    private HoneyPot pot;
    public BeeProducer(HoneyPot p, int i) {
        this.pot = p;
        new Thread(this, "Bee_"+i).start();
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            if(pot.checkState()) {
                try {
                    notifyAll();
                    break;
                }catch (IllegalStateException e) {}
            };
            pot.getPotSemaphore().getPermit();
            System.out.println("Sem gave permit: " + Thread.currentThread().getName());
            pot.enqueue(1);
            System.out.println(Thread.currentThread().getName() + " put 1 sip to HoneyPot!");
            pot.getPotSemaphore().getBackPermit();
        }

    }
}
