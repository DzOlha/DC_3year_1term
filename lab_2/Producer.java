package lab_2;

public class Producer implements Runnable {
    private Queue q;
    public Producer(Queue q, int size){
        this.q = q;
        this.q.setSize(size);
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        //for(int i = 0; i < 100; i++){
            q.putRowNumber(q.getSize());
        //}
        System.out.println("I am Producer and I completed putting values!");
    }
}
