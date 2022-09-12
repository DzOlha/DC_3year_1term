package lab_2;

public class Consumer implements Runnable{
    private Queue q;
    private boolean[][] Forest;
    public Consumer(Queue q, boolean[][] f){
        this.q = q;
        this.Forest = f;
        new Thread(this, "Consumer").start();
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            int row = q.getRowNumber();
            for(int j = 0; j < 100; j++){
                if(Forest[row][j] == true) {
                    q.setFoundBear();
                    System.out.println("Found Bear! Coordinates" + row + "; " + j);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
