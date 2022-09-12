package lab_2;
class QueueClassOperation
{
    protected int[] arr;
    protected int front;
    protected int rear;
    protected int capacity;
    protected int count;

    public QueueClassOperation(int size)
    {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    public int dequeue()
    {
        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }

        int x = arr[front];

        System.out.println("Removing " + x);

        front = (front + 1) % capacity;
        count--;

        return x;
    }

    public void enqueue(int item)
    {
        if (isFull())
        {
            System.out.println("Overflow\nProgram Terminated");
            System.exit(-1);
        }

        System.out.println("Inserting " + item);

        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
    }

    public int peek()
    {
        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }
        return arr[front];
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public boolean isFull() {
        return (size() == capacity);
    }
}

public class Queue extends QueueClassOperation{
    private boolean foundBear = false;
    private boolean valueSet = false;
    public Queue(int size){
        super(size);
    }
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
        int n = this.dequeue();
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

        this.enqueue(n);
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
