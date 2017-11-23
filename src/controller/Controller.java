package controller;

import model.Model;
import org.jfree.data.time.Minute;
import view.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Controller.
 */
public class Controller {

    private View view;
    private Model model;
    private SerialController serial;
    private long nextUpdate;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view){
        this.view = view;
        this.model = model;
        serial = SerialController.getInstance(model, view);

        addListeners();

        new Thread(() -> {
            int i = 0;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.addDataToCanetteSeries(model.getTemperatureCanette());
                view.addDataToExterieurSeries(model.getTemperatureExterne());
                view.addDataToInterieurSeries(model.getTemperatureInterne());
                if(pointRosee(model.getTemperatureExterne(), model.getHumidite()) > model.getTemperatureCanette()) {
                    if(i == 0){
                        i = 60;
                        view.alerteRosee();
                    }
                    i--;
                }
                else
                    i = 0;
            }
        }).start();

        new Thread(() -> {
            while(true){
                checkBigDifference();
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void addListeners(){
        view.addSliderListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int consigne = ((JSlider) e.getSource()).getValue();
                model.setTemperatureConsigne(consigne);
                view.setTempConsigne(consigne);

                Calendar rightNow = Calendar.getInstance();
                if (rightNow.getTimeInMillis() > nextUpdate) {
                    if (model.getTemperatureConsigne() < model.getTemperatureCanette())
                        serial.sendData("frigo:1");
                    else
                        serial.sendData("frigo:0");
                    if (serial.isConnected)
                        serial.sendConsigne(consigne);

                    rightNow.add(Calendar.SECOND, 30);
                    nextUpdate = rightNow.getTimeInMillis();
                }
            }
        });
    }

    private double pointRosee(double temp, int humid){
        double point;
        float humidite = (float) humid/100;
        point = ((17.27 * temp)/(237.7 + temp)) + Math.log(humidite);
        point = (237.7 * point) / (17.27 - point);
        return point;
    }

    private void checkBigDifference(){
        if(model.getLastEtatFrigo() && model.getEtatFrigo() && (model.getLastTemperatureCanette() < model.getTemperatureCanette())){
            view.alerteTemp();
        }
        model.setLastEtatFrigo(model.getEtatFrigo());
        model.setLastTemperatureCanette(model.getTemperatureCanette());
    }

}
