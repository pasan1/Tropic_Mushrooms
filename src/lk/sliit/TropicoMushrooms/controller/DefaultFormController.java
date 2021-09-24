package lk.sliit.TropicoMushrooms.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.DefaultBO;
import lk.sliit.TropicoMushrooms.dto.ChartDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultFormController {

    public AnchorPane window;
    public Text txtTotalCustomers;
    public Text txtTotalSales;
    public Text txtTodayDate;
    public Text txtTotalItems;
    public Text txtTotalOrders;
    public Text txtTotalPurchases;
    @FXML
    private BarChart<String, Double> chartDailySummery;

    DefaultBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.DEFAULT);

    public void initialize() throws Exception {
        loadChart();
        loadTotals();
    }

    private void loadTotals() {
        try {
            txtTotalCustomers.setText(String.valueOf(bo.getCountForCustomer()));
            txtTotalItems.setText(String.valueOf(bo.getCountForItems()));
            txtTotalOrders.setText(String.valueOf(bo.getCountForOrders()));
            txtTotalPurchases.setText(String.valueOf(bo.getCountForPurchases()));
            txtTodayDate.setText(String.valueOf(java.sql.Date.valueOf(LocalDate.now())));
            txtTotalSales.setText(String.valueOf(bo.getTodayTotalSale(java.sql.Date.valueOf(LocalDate.now()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadChart() throws Exception {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<ChartDTO> barchart = bo.barChart();

        for (ChartDTO chartDTO : barchart) {
            series.getData().add(new XYChart.Data<>(chartDTO.getDate(), chartDTO.getAmount()));
        }
        chartDailySummery.getData().add(series);
    }
}
