package lab_1;

import javax.swing.*;

public class MyRunnable implements Runnable{
    private int position;
    private JSlider sld;
    public MyRunnable(int num, JSlider sl) {
        this.position = num;
        this.sld = sl;
    }
    @Override
    public void run() {
        sld.setValue(position);
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + "was interrupted!");
        }
        System.out.println("I am " + Thread.currentThread().getName() + "! I changed slider value!");
    }
}
