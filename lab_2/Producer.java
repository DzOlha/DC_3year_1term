package lab_2;

public class Producer implements Runnable {
    private Queue q;
    public Producer(Queue q){
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            q.putRowNumber(i);
        }
    }
}
