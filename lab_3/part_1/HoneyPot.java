package lab_3.part_1;
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

    public boolean enqueue(int item)
    {
        if (isFull())
        {
            return false;
            //System.out.println("Overflow\nProgram Terminated");
            //System.exit(-1);
        }

        //System.out.println("Inserting " + item);

        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
        return true;
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
public class HoneyPot extends QueueClassOperation{
    private int fullPot;
    public void setFullPot(int flag){this.fullPot = flag;}
    public int getFullPot(){return fullPot;}
    private Semaphore potSemaphore;
    public Semaphore getPotSemaphore(){return potSemaphore;}
    public HoneyPot(int N, Semaphore sem) {
        super(N);
        this.potSemaphore = sem;
        this.fullPot = 0;
    }
    public boolean checkState(){
        synchronized (this){
            if(this.isFull()){
                System.out.println("--------------------POT IS FULL!!!!!!!!!!!!!------------------");
                this.fullPot = 1;
                return true;
            }
        }
        return false;
    }
}
