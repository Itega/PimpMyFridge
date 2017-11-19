package view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * The type Frame.
 */
public class Frame extends JFrame{

    /**
     * The Lab.
     */
    Label lab;


    /**
     * Instantiates a new Frame.
     *
     * @param panel the panel
     */
    public Frame(JPanel panel){

        this.setSize(400,200);
        this.setTitle("Frigo manager");
        this.setResizable(false);
        this.setLocation(500,250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * Update frame.
     *
     * @param inputLine the input line
     */
    public void updateFrame(String inputLine){
        lab.setText(inputLine + " C°");
    }

}
