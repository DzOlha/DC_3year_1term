package lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class Main {
    private static Thread th1;
    private static Thread th2;

    //1 - busy, 0 - empty.
    public static int busySemaphore;

    public static void checkBusySemaphore(int busySem, JLabel lab){
        if(busySem == 1){
            lab.setText("Critical section: BUSY!");
        }
        else if(busySem == 0){
            lab.setText("Critical section: FREE!");
        }
    }
    public static void checkButtons(JButton b1, JButton b2, boolean flag){
        b1.setEnabled(flag);
        b2.setEnabled(flag);
    }

    public static void main(String[] args) {
        GUI window = new GUI();
        busySemaphore = 0;
        th1 = new Thread(new MyRunnable(10, window.getSlider()));
        th2 = new Thread(new MyRunnable(90, window.getSlider()));

        //Start1
        window.getBtn()[0].addActionListener((ActionEvent e) -> {
                th1.setPriority(Thread.MIN_PRIORITY);
                if(busySemaphore == 0){
                    busySemaphore = 1;
                    th1.start();
                    checkBusySemaphore(busySemaphore, window.getCriticalLabel());
                    //set Stop2 button as disabled.
                    window.getBtn()[3].setEnabled(false);
                }
        });

        //Start2
        window.getBtn()[1].addActionListener((ActionEvent e) -> {
            th2.setPriority(Thread.MAX_PRIORITY);
            if(busySemaphore == 0){
                busySemaphore = 1;
                th2.start();
                checkBusySemaphore(busySemaphore, window.getCriticalLabel());
                //set Stop1 button as disabled.
                window.getBtn()[2].setEnabled(false);
            }
        });

        //Stop1
        window.getBtn()[2].addActionListener((ActionEvent e) -> {
            th1.stop();
            busySemaphore = 0;
            checkBusySemaphore(busySemaphore, window.getCriticalLabel());
            if(window.getBtn()[1].isEnabled()){
                checkButtons(window.getBtn()[1], window.getBtn()[3], true);
            }
            checkButtons(window.getBtn()[0], window.getBtn()[2], false);
        });

        //Stop2
        window.getBtn()[3].addActionListener((ActionEvent e) -> {
            th2.stop();
            busySemaphore = 0;
            checkBusySemaphore(busySemaphore, window.getCriticalLabel());
            if(window.getBtn()[0].isEnabled()){
                checkButtons(window.getBtn()[0], window.getBtn()[2], true);
            }
            checkButtons(window.getBtn()[1], window.getBtn()[3], false);
        });
    }
}
