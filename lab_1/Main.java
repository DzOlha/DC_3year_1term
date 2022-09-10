package lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class Main {
    private static Thread th1;
    private static Thread th2;

    public static void main(String[] args) {
        GUI window = new GUI();
        th1 = new Thread(new MyRunnable(10, window.getSlider(), window.getSpin1()));
        th2 = new Thread(new MyRunnable(90, window.getSlider(), window.getSpin2()));

        window.getBtn().addActionListener((ActionEvent e) -> {
                th1.start();
                th2.start();
        });
    }
}
