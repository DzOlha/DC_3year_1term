package lab_2.part_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private BlockingQueue<Integer> output;
    private int size;
    public Producer(BlockingQueue<Integer> numbersQueue, int size) {
        this.output = numbersQueue;
        this.size = size;
    }
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        Integer tmp = 0;
        for (int i = 0; i < size; i++) {
            tmp = ThreadLocalRandom.current().nextInt(50);
            try{
                output.put(tmp);
            }catch (InterruptedException ex){
                System.out.println("Error putting P!");
                Thread.currentThread().interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName() + ": " + tmp);
        }
    }
}
