package lab_1;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI {
    private JSlider slider;
    public JSlider getSlider(){
        return slider;
    }
    //----------------------------------------------
    private JButton btn;
    public JButton getBtn() {
        return btn;
    }
    //-----------------------------------------------
    private JSpinner spin1;
    public JSpinner getSpin1(){
        return spin1;
    }
    //-------------------------------------------------
    private JSpinner spin2;
    public JSpinner getSpin2(){
        return spin2;
    }
    //--------------------------------------------------
    public GUI(){
        //create the window for the GUI
        JFrame win = new JFrame();
        win.setDefaultCloseOperation(EXIT_ON_CLOSE);
        win.setSize(500,600);

        //set fonts for the different form elements
        String fontName = "Arial";
        FontUIResource fontButton = new FontUIResource(fontName, Font.PLAIN, 30);
        FontUIResource fontSpinner = new FontUIResource(fontName, Font.PLAIN, 20);
        FontUIResource fontSlider = new FontUIResource(fontName, Font.BOLD, 18);

        //create the general panel inside the window
        JPanel panel = new JPanel();
        panel.setSize(400, 500);
        //set grid layout for all content inside the panel
        panel.setLayout(new GridLayout(8, 1, 80, 20));

        //create slider with the labels of the points.
        slider = new JSlider(0,100,50);
        slider.setFont(fontSlider);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //create separate panel for two spinners
        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new GridLayout(1, 2, 50, 50));
        spinnerPanel.setSize(300, 100);

        //create the first spinner
        spin1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spin1.setFont(fontSpinner);

        //create the second spinner
        spin2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spin2.setFont(fontSpinner);

        //create the "Start" button that runs two threads.
        btn = new JButton("Start");
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
