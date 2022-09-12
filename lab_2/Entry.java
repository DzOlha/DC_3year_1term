package lab_2;

import java.sql.Array;

public class Entry {
    private static boolean[][] forestArray;
    public static void fillArray(int x, int y){
        int size = 10;
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
        fillArray(0, 1);

    }
}
