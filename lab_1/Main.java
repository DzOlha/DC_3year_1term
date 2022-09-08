package lab_1;

import javax.swing.*;

public class Main {
    private static Thread firstThread;
    private static Thread secondThread;
    private static final JSlider slider = new JSlider();

    public static void main(String[] args) {
        System.out.println("Hello");
        GUI window = new GUI();
        window.setGUI();
    }
}
