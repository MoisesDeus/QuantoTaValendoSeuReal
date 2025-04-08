package br.com.mddeveloper.Service;

import br.com.mddeveloper.API.CurrencyFifteenApiClient;
import br.com.mddeveloper.Model.CurrencyFifteen;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.io.File;
import java.util.Date;
import java.util.List;

public class ChartService {
    public void generateChart(List<CurrencyFifteen> currencies) {
        System.setProperty("java.awt.headless", "true");

        TimeSeries series = new TimeSeries("USD/BRL");
        for (CurrencyFifteen currency : currencies) {
            long timestampMs = Long.parseLong(currency.getTimestamp()) * 1000;
            Date date = new Date(timestampMs);
            series.addOrUpdate(new Day(date), currency.getValue());
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Cotação USD/BRL - Últimos 15 dias",
                "Data",
                "Valor (R$)",
                dataset
        );
        try {
            ChartUtils.saveChartAsPNG(new File("chart.png"), chart, 800, 600);
            System.out.println("Gráfico gerado com sucesso em chart.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
