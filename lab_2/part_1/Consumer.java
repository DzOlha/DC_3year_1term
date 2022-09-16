package lab_2.part_1;

import lab_2.part_1.Queue;

public class Consumer implements Runnable{
    private Queue q;
    private boolean[][] Forest;
    public Consumer(Queue q, boolean[][] f, int i){
        this.q = q;
        this.Forest = f;
        new Thread(this, "Consumer_"+i).start();
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            int row = q.getRowNumber();
            int n = q.getSize();

            //reach the end of the queue = > checked all parts of the forest
            if(row == -1)
                break;
            //-----------------------------------------------------------------
            System.out.println("I am " + Thread.currentThread().getName());
            for(int j = 0; j < n; j++)
            {
                System.out.println("I am " + Thread.currentThread().getName() + " => Part = " + row + ". Step = " + j);
                if(Forest[row][j] == true)
                {
                    System.out.println("\n-------------------------------Found Bear! Coordinates [" + row + "; " + j + "]\n");
                    break;
                }
            }
            Thread.currentThread().interrupt();
        }
    }
}
