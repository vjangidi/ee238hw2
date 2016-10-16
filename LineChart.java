package hw2;

import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineChart(final String title, Set<Map.Entry<Integer, Double>> state2Values) {

        super(title);

        final XYDataset dataset = createDataset(state2Values);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createDataset(Set<Map.Entry<Integer, Double>> state2Values) {
        
        final XYSeries series2 = new XYSeries("Plot Of State 2 StateValues");
        for(Entry<Integer, Double> e : state2Values){
        	series2.add(e.getKey(), e.getValue());
        }
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series2);                
        return dataset;
        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Line Chart State To Value",      // chart title
            "States",                      // x axis label
            "StateValues",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                
        return chart;
        
    }
}

