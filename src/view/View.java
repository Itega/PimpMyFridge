package view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * The type View.
 */
public class View {

    private Frame frame;
    private JPanel panel;
    private Label lab;
    private Label value;
    private Slider slider;

    /**
     * Instantiates a new View.
     */
    public View(){
        panel = new JPanel();
        frame = new Frame(panel);
        panel.setLayout(new BorderLayout());
        lab = new Label();

        value = new Label();
        value.setText("15 °C");

        slider = new Slider();

        panel.add(value, BorderLayout.SOUTH);
        panel.add(lab, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        refresh();
    }

    /**
     * Refresh.
     */
    public void refresh(){
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Add slider listener.
     *
     * @param changeListener the change listener
     */
    public void addSliderListener(ChangeListener changeListener){
        slider.addChangeListener(changeListener);
    }

    /**
     * Set consigne.
     *
     * @param consigne the consigne
     */
    public void setConsigne(int consigne){
        value.setText(consigne + " °C");
    }
}
