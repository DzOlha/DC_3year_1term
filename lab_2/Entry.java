package lab_2;

import java.sql.Array;

public class Entry {
    private static boolean[][] forestArray;
    public static void fillArray(int x, int y, int size){
        forestArray = new boolean[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                forestArray[i][j] = false;

                if(i == x && j == y)
                    forestArray[i][j] = true;
            }
        }
    }
    public static void main(String[] args) {
        int size = 10;
        fillArray(2, 1, size);
        Queue Q = new Queue(size);
        new Producer(Q, size);

        for(int i = 0; i < 6; i++){
            new Consumer(Q,forestArray, i);
        }
    }
}
