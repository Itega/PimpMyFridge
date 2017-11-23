package view;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Series;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

import java.awt.*;

/**
 * The type Chart.
 */
public class Chart extends ChartPanel{


    /**
     * Instantiates a new Chart.
     *
     * @param chart the chart
     */
    public Chart(JFreeChart chart){
        super(chart);
        chart.setBackgroundPaint(Color.decode("#353536"));
        chart.setBorderPaint(Color.white);
        setFontColor(Color.decode("#0077AC"));
    }

    private void setFontColor(Color fontColor) {
        JFreeChart chart = getChart();
        chart.getTitle().setPaint(fontColor);
        Plot plot = chart.getPlot();
        if (plot instanceof CategoryPlot) {
            setAxisFontColor(((CategoryPlot) plot).getDomainAxis(), fontColor);
            setAxisFontColor(((CategoryPlot) plot).getRangeAxis(), fontColor);
        } else if (plot instanceof XYPlot) {
            setAxisFontColor(((XYPlot) plot).getDomainAxis(), fontColor);
            setAxisFontColor(((XYPlot) plot).getRangeAxis(), fontColor);
        }
    }

    private void setAxisFontColor(Axis axis, Color fontColor) {
        if (!fontColor.equals(axis.getLabelPaint()))
            axis.setLabelPaint(fontColor);
        if (!fontColor.equals(axis.getTickLabelPaint()))
            axis.setTickLabelPaint(fontColor);
    }
}
