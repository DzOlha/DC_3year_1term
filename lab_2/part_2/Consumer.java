package lab_2.part_2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> input;
    public Consumer(BlockingQueue<Integer> in) {
        this.input = in;
    }
    public void run() {
        Integer tmp = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                tmp = input.take();
            } catch(InterruptedException ex){
                System.out.println("Error taking C!");
                Thread.currentThread().interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName() + ": " + tmp);
        }
    }
}
