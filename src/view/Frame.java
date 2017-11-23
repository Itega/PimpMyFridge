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
     */
    public Frame(){

        this.setSize(1100,600);
        this.setTitle("Pimp My Fridge");
        this.setResizable(true);
        this.setLocation(500,250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
