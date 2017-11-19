package model;

import controller.SerialController;

/**
 * The type Model.
 */
public class Model {
    private double temperatureActuelle = 0;
    private float temperatureConsigne = 0;
    private float humidite = 0;

    /**
     * Instantiates a new Model.
     */
    public Model(){

    }

    /**
     * Gets temperature consigne.
     *
     * @return the temperature consigne
     */
    public float getTemperatureConsigne() {
        return temperatureConsigne;
    }

    /**
     * Sets temperature consigne.
     *
     * @param temperatureConsigne the temperature consigne
     */
    public void setTemperatureConsigne(float temperatureConsigne) {
        this.temperatureConsigne = temperatureConsigne;
    }

    /**
     * Gets temperature actuelle.
     *
     * @return the temperature actuelle
     */
    public double getTemperatureActuelle() {
        return temperatureActuelle;
    }

    /**
     * Sets temperature actuelle.
     *
     * @param temperatureActuelle the temperature actuelle
     */
    public void setTemperatureActuelle(double temperatureActuelle) {
        this.temperatureActuelle = temperatureActuelle;
    }

    /**
     * Gets humidite.
     *
     * @return the humidite
     */
    public float getHumidite() {
        return humidite;
    }

    /**
     * Sets humidite.
     *
     * @param humidite the humidite
     */
    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
