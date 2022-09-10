package lab_1;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI {
    private JSlider slider;
    public JSlider getSlider(){
        return slider;
    }
    //----------------------------------------------
    //btns[0] = Start1, btns[1] = Start2, btns[2] = Stop1, btns[3] = Stop2
    private JButton[] btns = new JButton[4];
    public JButton[] getBtn() {
        return btns;
    }
    //-----------------------------------------------
    private JLabel criticalLabel;
    public JLabel getCriticalLabel(){return criticalLabel; }
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

        //create panels for button groups
        JPanel btn1 = new JPanel();
        btn1.setLayout(new GridLayout(1, 2, 50, 50));

        JPanel btn2 = new JPanel();
        btn2.setLayout(new GridLayout(1, 2, 50, 50));

        //create the "Start" and "Stop" buttons.
        btns[0] = new JButton("Start 1");
        btns[0].setFont(fontButton);

        btns[1] = new JButton("Start 2");
        btns[1].setFont(fontButton);

        btns[2] = new JButton("Stop 1");
        btns[2].setFont(fontButton);

        btns[3] = new JButton("Stop 2");
        btns[3].setFont(fontButton);

        //place buttons onto panels
        btn1.add(btns[0]);
        btn1.add(btns[2]);

        btn2.add(btns[1]);
        btn2.add(btns[3]);

        //added mark for status of availability the critical section
        criticalLabel = new JLabel("Critical section: FREE!");
        criticalLabel.setFont(fontButton);

        //place slider + spinnerPanel + button inside the general panel space
        panel.add(criticalLabel);
        panel.add(slider);
        panel.add(btn1);
        panel.add(btn2);

        //place the general panel to the whole form window.
        win.setContentPane(panel);
        win.setVisible(true);
    }
}
