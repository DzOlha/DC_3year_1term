package lab_2.part_2;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Integer amountOfProducts = 10;
        BlockingQueue<Integer> queueFirst = new LinkedBlockingQueue<>(amountOfProducts);
        BlockingQueue<Integer> queueSecond = new LinkedBlockingQueue<>(amountOfProducts);

        Thread producer = new Thread(new Producer(queueFirst, amountOfProducts), "producer ");
        Thread producerConsumer = new Thread(new ProducerConsumer(queueFirst, queueSecond), "producerConsumer ");
        Thread consumer = new Thread(new Consumer(queueSecond), "consumer ");

        producer.start();
        producerConsumer.start();
        consumer.start();
    }
}
