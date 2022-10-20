package module_1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class task4Java {
    public static void main(String[] args) {
        Operator op1 = new Operator(1);
        Operator op2 = new Operator(2);
        Thread operator_1 = new Thread(op1, "Operator_1");
        Thread operator_2 = new Thread(op2, "Operator_2");

        Thread caller_1 = new Thread (new Caller(op1, 1), "Caller_1");
        Thread caller_2 = new Thread (new Caller(op2, 2), "Caller_2");
        Thread caller_3 = new Thread (new Caller(op1, 3), "Caller_3");
        Thread caller_4 = new Thread (new Caller(op2, 4), "Caller_4");
        Thread caller_5 = new Thread (new Caller(op2, 5), "Caller_5");
        operator_1.start();
        operator_2.start();
        caller_1.start();  caller_2.start();  caller_3.start();  caller_4.start();  caller_5.start();
    }
    private static class Operator implements Runnable {
        private final Semaphore semaphore = new Semaphore(1);
        private final Queue<Caller> callers = new ConcurrentLinkedDeque<>();
        private int num;
        public int getNum(){return num;}
        public Queue<Caller> getCallers(){
            return callers;
        }
        public Operator(int i){
            this.num = i;
        }
        public void addCallerToQueue(Caller caller){
            callers.add(caller);
            System.out.println(Thread.currentThread().getName() + " went into the queue of Operator_" + getNum());
        }
        public void callerCancelCall(Caller caller){
            callers.remove(caller);
            System.out.println(Thread.currentThread().getName() + " LEFT the queue of Operator_" + getNum());
        }
        @Override
        public void run() {
            while(callers.isEmpty()) {
                try {Thread.sleep(200);}
                catch (InterruptedException e) {}
            }
            while(!callers.isEmpty()) {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " have conversation with caller_" + callers.remove().getNum());
                    System.out.println(Thread.currentThread().getName() + " I am free!");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " Queue is empty!");
        }
    }
    private static class Caller implements Runnable {
        private final Operator operator;
        private final int num;
        public Caller(Operator operator, int number) {
            this.operator = operator;
            this.num = number;
        }
        public int getNum(){return num;}
        @Override
        public void run() {
            operator.addCallerToQueue(this);
            try {
                Thread.sleep(185);
            }catch(InterruptedException exp) {}

            if(operator.getCallers().contains(this)) {
                operator.callerCancelCall(this);
                operator.addCallerToQueue(this);
            }
        }
    }
}
