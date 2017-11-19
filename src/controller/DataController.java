package controller;

import model.Model;

/**
 * The type Data controller.
 */
public class DataController {

    /**
     * The Model.
     */
    Model model;

    /**
     * Instantiates a new Data controller.
     *
     * @param model the model
     */
    public DataController(Model model) {
        this.model = model;
    }

    /**
     * Process data.
     *
     * @param data the data
     */
    public void processData(String data){
        //Transforme les données envoyées par l'arduino en données utilisable par notre programme et met à jour le model.
    }

}
