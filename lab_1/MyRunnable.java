package lab_1;

import javax.swing.*;

public class MyRunnable implements Runnable{
    private int position;
    private JSlider sld;
    private JSpinner spinner;
    public MyRunnable(int num, JSlider sl, JSpinner spin) {
        this.position = num;
        this.sld = sl;
        this.spinner = spin;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            Thread.currentThread().setPriority((int)spinner.getValue());
            synchronized (sld) {
                sld.setValue(position);
                try{
                    Thread.sleep(500);
                    sld.setValue(50);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName() + "was interrupted!");
                }
                System.out.println("I am " + Thread.currentThread().getName() + "! I changed slider value!");
            }
        }
    }
}
