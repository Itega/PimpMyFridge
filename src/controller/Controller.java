package controller;

import model.Model;
import view.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The type Controller.
 */
public class Controller {

    private View view;
    private Model model;
    private SerialController serial;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view){
        this.view = view;
        this.model = model;
        serial = SerialController.getInstance(model);

        addListeners();
    }

    private void addListeners(){
        view.addSliderListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int consigne = ((JSlider) e.getSource()).getValue();
                model.setTemperatureConsigne(consigne);
                view.setConsigne(consigne);
                if(serial.isConnected)
                    serial.sendConsigne(consigne);
            }
        });
    }
}
