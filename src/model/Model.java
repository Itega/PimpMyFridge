package model;

import controller.SerialController;

import java.util.LinkedList;

/**
 * The type Model.
 */
public class Model {
    private float temperatureCanette = 0;
    private float temperatureInterne = 0;
    private float temperatureExterne = 0;
    private int temperatureConsigne = 0;
    private int humidite = 0;
    private Boolean lastEtatFrigo = false;
    private Boolean etatFrigo = false;
    private float lastTemperatureCanette = 0;

    /**
     * Instantiates a new Model.
     */
    public Model(){

    }

    /**
     * Gets temperature canette.
     *
     * @return the temperature canette
     */
    public float getTemperatureCanette() {
        return temperatureCanette;
    }

    /**
     * Sets temperature canette.
     *
     * @param temperatureCanette the temperature canette
     */
    public void setTemperatureCanette(float temperatureCanette) {
        this.temperatureCanette = temperatureCanette;
    }

    /**
     * Gets temperature interne.
     *
     * @return the temperature interne
     */
    public float getTemperatureInterne() {
        return temperatureInterne;
    }

    /**
     * Sets temperature interne.
     *
     * @param temperatureInterne the temperature interne
     */
    public void setTemperatureInterne(float temperatureInterne) {
        this.temperatureInterne = temperatureInterne;
    }

    /**
     * Gets temperature externe.
     *
     * @return the temperature externe
     */
    public float getTemperatureExterne() {
        return temperatureExterne;
    }

    /**
     * Sets temperature externe.
     *
     * @param temperatureExterne the temperature externe
     */
    public void setTemperatureExterne(float temperatureExterne) {
        this.temperatureExterne = temperatureExterne;
    }

    /**
     * Gets temperature consigne.
     *
     * @return the temperature consigne
     */
    public int getTemperatureConsigne() {
        return temperatureConsigne;
    }

    /**
     * Sets temperature consigne.
     *
     * @param temperatureConsigne the temperature consigne
     */
    public void setTemperatureConsigne(int temperatureConsigne) {
        this.temperatureConsigne = temperatureConsigne;
    }

    /**
     * Gets humidite.
     *
     * @return the humidite
     */
    public int getHumidite() {
        return humidite;
    }

    /**
     * Sets humidite.
     *
     * @param humidite the humidite
     */
    public void setHumidite(int humidite) {
        this.humidite = humidite;
    }

    /**
     * Gets last etat frigo.
     *
     * @return the last etat frigo
     */
    public Boolean getLastEtatFrigo() {
        return lastEtatFrigo;
    }

    /**
     * Sets last etat frigo.
     *
     * @param lastEtatFrigo the last etat frigo
     */
    public void setLastEtatFrigo(Boolean lastEtatFrigo) {
        this.lastEtatFrigo = lastEtatFrigo;
    }

    /**
     * Gets etat frigo.
     *
     * @return the etat frigo
     */
    public Boolean getEtatFrigo() {
        return etatFrigo;
    }

    /**
     * Sets etat frigo.
     *
     * @param etatFrigo the etat frigo
     */
    public void setEtatFrigo(Boolean etatFrigo) {
        this.etatFrigo = etatFrigo;
    }

    /**
     * Gets last temperature canette.
     *
     * @return the last temperature canette
     */
    public float getLastTemperatureCanette() {
        return lastTemperatureCanette;
    }

    /**
     * Sets last temperature canette.
     *
     * @param lastTemperatureCanette the last temperature canette
     */
    public void setLastTemperatureCanette(float lastTemperatureCanette) {
        this.lastTemperatureCanette = lastTemperatureCanette;
    }
}
