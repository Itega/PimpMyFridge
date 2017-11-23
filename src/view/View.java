package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Date;

/**
 * The type View.
 */
public class View {

    private Frame frame;
    private JPanel panel;
    private JPanel tempPanel;
    private Label consigne;
    private Label interieure;
    private Label humidite;
    private Label canette;
    private Label exterieure;
    private Slider slider;
    private Chart chart;

    /**
     * Instantiates a new View.
     */
    public View(){
        panel = new JPanel();
        tempPanel = new JPanel();
        frame = new Frame();
        chart = new Chart(createChart());

        panel.setBackground(Color.decode("#353536"));
        tempPanel.setBackground(Color.decode("#353536"));
        chart.setForeground(Color.decode("#0077AC"));

        frame.setContentPane(panel);
        panel.setLayout(new GridBagLayout());


        consigne = new Label();
        consigne.setText("Temperature consigne : 15 °C");

        interieure = new Label();
        interieure.setText("Temperature intérieure : 15 °C");

        canette = new Label();
        canette.setText("Temperature canette : 15 °C");

        exterieure = new Label();
        exterieure.setText("Temperature extérieure : 15 °C");

        humidite = new Label();
        humidite.setText("Humidité : 77%");

        tempPanel.setLayout(new GridLayout(5,1));


        tempPanel.add(consigne);
        tempPanel.add(interieure);
        tempPanel.add(humidite);
        tempPanel.add(canette);
        tempPanel.add(exterieure);

        slider = new Slider();

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 6;
        c.gridwidth = 8;
        c.weightx = c.weighty = 1;
        c.fill = c.BOTH;
        c.insets = new Insets(0,10,0,10);
        panel.add(chart, c);
        c.gridx = 8;
        c.gridy = 0;
        c.gridheight = 6;
        c.gridwidth = 4;
        c.weightx = c.weighty = 0;
        c.fill = c.BOTH;
        c.insets = new Insets(0,10,0,10);
        panel.add(tempPanel, c);
        c.gridx = 0;
        c.gridy = 12;
        c.gridheight = 3;
        c.gridwidth = 12;
        c.insets = new Insets(0,30,0,30);
        c.fill = c.BOTH;
        c.weightx = c.weighty = 1;
        panel.add(slider,c);
        refresh();

        frame.revalidate();
        frame.repaint();
    }
    private TimeSeriesCollection dataset;
    private TimeSeries canetteSeries;
    private TimeSeries interieurSeries;
    private TimeSeries exterieurSeries;

    /**
     * Add data to canette series.
     *
     * @param data the data
     */
    public void addDataToCanetteSeries(double data){
        canetteSeries.add(new Second(new Date()), data);
    }

    /**
     * Add data to interieur series.
     *
     * @param data the data
     */
    public void addDataToInterieurSeries(double data){
        interieurSeries.add(new Second(new Date()), data);
    }

    /**
     * Add data to exterieur series.
     *
     * @param data the data
     */
    public void addDataToExterieurSeries(double data){
        exterieurSeries.add(new Second(new Date()), data);
    }

    /**
     * Create chart j free chart.
     *
     * @return the j free chart
     */
    public JFreeChart createChart(){
        canetteSeries = new TimeSeries("Canette", Second.class);
        canetteSeries.setMaximumItemAge(3600);

        interieurSeries = new TimeSeries("Interieur", Second.class);
        interieurSeries.setMaximumItemAge(3600);

        exterieurSeries = new TimeSeries("Exterieur", Second.class);
        exterieurSeries.setMaximumItemAge(3600);

        dataset = new TimeSeriesCollection();
        dataset.addSeries(canetteSeries);
        dataset.addSeries(interieurSeries);
        dataset.addSeries(exterieurSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Temperature en fonction du temps",
                "Temps (min)", "Temperature (°C)",
                dataset,
                true,
                true,
                false
        );
        return chart;
    }

    /**
     * Refresh.
     */
    public void refresh(){
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Alerte rosee.
     */
    public void alerteRosee(){
        JOptionPane.showMessageDialog(null, "Il y a risque de condensation.");
    }

    /**
     * Alerte temp.
     */
    public void alerteTemp(){
        JOptionPane.showMessageDialog(null, "Variation de température anormale.");
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
     * @param temp the temp
     */
    public void setTempConsigne(int temp){
        this.consigne.setText("Temperature consigne : " + temp + " °C");
    }

    /**
     * Set temp canette.
     *
     * @param temp the temp
     */
    public void setTempCanette(float temp){
        this.canette.setText("Temperature canette : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set temp externe.
     *
     * @param temp the temp
     */
    public void setTempExterne(float temp){
        this.exterieure.setText("Temperature extérieure : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set temp interne.
     *
     * @param temp the temp
     */
    public void setTempInterne(float temp){
        this.interieure.setText("Temperature intérieure : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set humidite.
     *
     * @param temp the temp
     */
    public void setHumidite(int temp){
        this.humidite.setText("Humidité : " + temp + "%");
    }
}
