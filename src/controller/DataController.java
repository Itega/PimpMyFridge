package controller;

import model.Model;
import view.View;

import java.awt.*;
import java.util.LinkedList;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * The type Data controller.
 */
public class DataController {

    /**
     * The Model.
     */
    Model model;
    /**
     * The View.
     */
    View view;

    /**
     * Instantiates a new Data controller.
     *
     * @param model the model
     * @param view  the view
     */
    public DataController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Process data.
     *
     * @param data the data
     */
    public void processData(String data){

        if(data.startsWith("#") && data.endsWith("#") && data.length() > 2) {
            data = data.split("#")[1];
            if(!data.contains("NAN")){
                switch(data.toCharArray()[0]){
                    case 't':
                        float t = (float) tempThermistance(Integer.parseInt(data.split("t")[1]));
                        model.setTemperatureCanette(t);
                        view.setTempCanette(t);
                        break;
                    case 'h':
                        int h = round(Float.parseFloat(data.split("h")[1]));
                        model.setHumidite(h);
                        view.setHumidite(h);
                        break;
                    case 'i':
                        float i = Float.parseFloat(data.split("i")[1]);
                        model.setTemperatureInterne(i);
                        view.setTempInterne(i);
                        break;
                    case 'e':
                        float e = Float.parseFloat(data.split("e")[1]);
                        model.setTemperatureExterne(e);
                        view.setTempExterne(e);
                        break;
                }
            }
        }

//        System.out.println(data);
        //Transforme les données envoyées par l'arduino en données utilisable par notre programme et met à jour le model.
    }

    private double tempThermistance(int data){
        double A=0.00104,B=0.00024,C=1.50069E-7,LR,vOut,vIn = 5, RT, R2 =10000;
        vOut = (data*vIn)/1023;
        RT = ((R2*vIn)/vOut)-R2;
        LR = log(RT);
        return (1/(A+B*LR+C*pow(LR,3)))-273.15;
    }
}
