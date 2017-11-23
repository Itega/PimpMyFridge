package view;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

/**
 * The type Slider.
 */
public class Slider extends JSlider
{

    /**
     * Instantiates a new Slider.
     */
    public Slider() {
        this.setMaximum(30);
        this.setMinimum(0);
        this.setValue(15);
        this.setBackground(Color.decode("#353536"));
        this.setForeground(Color.decode("#0077AC"));
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMinorTickSpacing(1);
        this.setMajorTickSpacing(5);
    }
}
