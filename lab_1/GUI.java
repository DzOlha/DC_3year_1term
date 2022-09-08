package lab_1;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class GUI {
    public static void setGUI(){
        //create the window for the GUI
        JFrame win = new JFrame();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(500,600);

        //set fonts for the different form elements
        FontUIResource fontButton = new FontUIResource("Arial", Font.PLAIN, 30);
        FontUIResource fontSpinner = new FontUIResource("Arial", Font.PLAIN, 20);
        FontUIResource fontSlider = new FontUIResource("Arial", Font.BOLD, 18);

        //create the general panel inside the window
        JPanel panel = new JPanel();
        panel.setSize(400, 500);
        //set grid layout for all content inside the panel
        panel.setLayout(new GridLayout(8, 1, 80, 20));

        //create slider with the labels of the points.
        JSlider slider = new JSlider(0,100,50);
        slider.setFont(fontSlider);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //create separate panel for two spinners
        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new GridLayout(1, 2, 50, 50));
        spinnerPanel.setSize(300, 100);

        //create the first spinner
        JSpinner spin1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spin1.setFont(fontSpinner);

        //create the second spinner
        JSpinner spin2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spin2.setFont(fontSpinner);

        //create the "Start" button that runs two threads.
        JButton btn = new JButton("Start");
        btn.setFont(fontButton);

        //place spinners inside their pannels
        spinnerPanel.add(spin1);
        spinnerPanel.add(spin2);

        //place slider + spinnerPanel + button inside the general panel space
        panel.add(slider);
        panel.add(spinnerPanel);
        panel.add(btn);

        //place the general panel to the whole form window.
        win.setContentPane(panel);
        win.setVisible(true);
    }
}
