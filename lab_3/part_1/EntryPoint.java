package lab_3.part_1;

public class EntryPoint {
    public static void main(String[] args) {
        int n = 10;//number of bees
        int N = 5;//number of sips of honey spot
        Semaphore sem = new Semaphore();
        HoneyPot myPot = new HoneyPot(N,sem);
        Thread bear = new Thread(new BearConsumer(myPot), "BEAR");
        bear.setDaemon(true);
        //bear.setPriority(10);
        bear.start();
        for (int i = 0; i < n; i++){
            new BeeProducer(myPot, i);
        }
    }
}
