//package controller.dashBoard;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.chart.*;
//import javafx.scene.control.Label;
//import javafx.scene.control.Tooltip;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class DashboardController implements Initializable {
//
//    public AreaChart chartsArea;
//    public LineChart yearlyOrderRateChart;
//    public CategoryAxis xAxis;
//    public NumberAxis yAxis;
//    public VBox revenueCard;
//    public VBox costsCard;
//    public VBox profitsCard;
//    public VBox shipmentsCard;
//    public VBox cardRoot;
//    public Label titleLabel;
//    public Label changeLabel;
//    @FXML
//    private AnchorPane root;
//
//
//    @FXML
//    void btnViewItemOnAction(ActionEvent event) {
//        URL resource = this.getClass().getResource("/view/Items.fxml");
//        assert resource != null;
//
//        try {
//            Parent load = FXMLLoader.load(resource);
//            this.root.getChildren().clear();
//            this.root.getChildren().add(load);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @FXML
//    void btnvVewCustomerOnAction(ActionEvent event) {
//        URL resource = this.getClass().getResource("/view/customer.fxml");
//        String cssPath = "/Controller/customer-styles.css";
//        String css = getClass().getResource(cssPath).toExternalForm();
//
//        root.getStylesheets().add(css);
//        assert resource != null;
//
//        try {
//            Parent load = FXMLLoader.load(resource);
//            this.root.getChildren().clear();
//            this.root.getChildren().add(load);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void btnViewOrderFormOnAcction(ActionEvent actionEvent) {
//        URL resource = this.getClass().getResource("/view/order-form.fxml");
//        assert resource != null;
//
//        try {
//            Parent load = FXMLLoader.load(resource);
//            this.root.getChildren().clear();
//            this.root.getChildren().add(load);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
//}
package controller.dashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.SuperService;
import service.custom.DashBoardService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Label txtRevenue;
    @FXML
    private AnchorPane root;

    @FXML
    private LineChart<String, Number> lineChart;
    DashBoardService serviceFactoryType = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.DASHBOARD);

//    @FXML
//    public void initialize() {
//        // Make sure lineChart is not null before using it
//        if (lineChart != null) {
//            loadChartData();
//        }
//    }

    private void loadChartData() {
        // Create series 1
       XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2024 Sales");


        try {
            serviceFactoryType.getSalesCharts().forEach(salesCharts -> {
                        series.getData().add(new XYChart.Data<>(salesCharts.getMonth(), salesCharts.getSales()));
                    }
            );
        }catch (RuntimeException r){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Create series 2
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Week 32");

        series2.getData().add(new XYChart.Data<>("Jan", 10));
        series2.getData().add(new XYChart.Data<>("Feb", 20));
        series2.getData().add(new XYChart.Data<>("Mar", 30));
        series2.getData().add(new XYChart.Data<>("Apr", 15));
        series2.getData().add(new XYChart.Data<>("May", 35));
        series2.getData().add(new XYChart.Data<>("Jun", 45));
        series2.getData().add(new XYChart.Data<>("Jul", 40));
        series2.getData().add(new XYChart.Data<>("Aug", 35));
        series2.getData().add(new XYChart.Data<>("Sep", 40));
        series2.getData().add(new XYChart.Data<>("Oct", 55));

        // Add series to chart
        lineChart.getData().addAll( series,series2);
    }


    @FXML
    public void btnViewOrderFormOnAcction() {
        URL resource = this.getClass().getResource("/view/order-form.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            this.root.getChildren().clear();
            this.root.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnViewCustomerOnAction() {
        URL resource = this.getClass().getResource("/view/customer.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            this.root.getChildren().clear();
            this.root.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnViewItemOnAction() {
        URL resource = this.getClass().getResource("/view/Items.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            this.root.getChildren().clear();
            this.root.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setRevenue(){
        try {
            txtRevenue.setText(String.format("%.0f",serviceFactoryType.getRevenue()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChartData();
        setRevenue();
    }
}