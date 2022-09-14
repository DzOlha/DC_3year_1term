package lab_2;
class QueueClassOperation
{
    protected int[] arr;
    protected int front;
    protected int rear;
    protected int capacity;
    public int getSize(){return capacity;}
    public void setSize(int size){this.capacity = size;}

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
            return -1;
        }

        int x = arr[front];

        //System.out.println("Removing " + x);

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

        //System.out.println("Inserting " + item);

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
    private boolean valueSet = true;
    private boolean queueFilled = false;
    public Queue(int size){
        super(size);
    }
    public boolean getFoundBear(){
        return foundBear;
    }
    public void setFoundBear(int x, int y){
        this.foundBear = true;
    }
    synchronized int getRowNumber() {
        while (!queueFilled){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        int n = this.dequeue();
        if(n != -1) {
            System.out.println("Got: " + n);
        }
        valueSet = true;
        return n;
    }
    synchronized void putRowNumber(int n) {
//        while(valueSet) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                System.out.println("InterruptedException caught");
//            }
//        }
        if(!queueFilled){
            for(int i = 0; i < n; i++){
                this.enqueue(i);
                System.out.println("Put: " + i);
            }
            queueFilled = true;
            notify();
        }
//        this.enqueue(n);
//        valueSet = true;
//        System.out.println("Put: " + n);
//        notify();
    }
}
