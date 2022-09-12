package lab_2;

public class Queue {
    private int n;
    private boolean foundBear = false;
    private boolean valueSet = false;
    public boolean getFoundBear(){
        return foundBear;
    }
    public void setFoundBear(){
        this.foundBear = true;
    }
    synchronized int getRowNumber() {
        while (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void putRowNumber(int n) {
        while(valueSet)
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException caught");
            }

        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
