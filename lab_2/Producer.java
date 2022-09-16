package lab_2;

public class Producer implements Runnable {
    private Queue q;
    public Producer(Queue q){
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        q.putRowNumber(q.getSize());
        System.out.println("I am Producer and I completed putting values!");
    }
}
