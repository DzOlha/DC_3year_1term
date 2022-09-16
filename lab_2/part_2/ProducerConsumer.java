package lab_2.part_2;

import java.util.concurrent.BlockingQueue;

public class ProducerConsumer implements Runnable{
    private BlockingQueue<Integer> input;
    private BlockingQueue<Integer> output;
    public ProducerConsumer(BlockingQueue<Integer> in, BlockingQueue<Integer> out) {
        this.input = in;
        this.output = out;
    }
    public void run() {
        Integer number = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try{
                number = input.take();
            }catch(InterruptedException e) {
                System.out.println("Error taking PC!");
                Thread.currentThread().interrupt();
                break;
            }
            try {
                output.put(number);
            }catch(InterruptedException exp) {
                System.out.println("Error PC insert!");
                Thread.currentThread().interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName() + ": " + number);
        }
    }
}
