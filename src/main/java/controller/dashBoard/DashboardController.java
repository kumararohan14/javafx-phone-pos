package controller.dashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public AreaChart chartsArea;
    @FXML
    private AnchorPane root;


    @FXML
    void btnViewItemOnAction(ActionEvent event) {
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

    @FXML
    void btnvVewCustomerOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/customer.fxml");
        String cssPath = "/Controller/customer-styles.css";
        String css = getClass().getResource(cssPath).toExternalForm();

        root.getStylesheets().add(css);
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            this.root.getChildren().clear();
            this.root.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnViewOrderFormOnAcction(ActionEvent actionEvent) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2025 Revenue");

        // Add data points with months
        series.getData().add(new XYChart.Data<>("January", 5000));
        series.getData().add(new XYChart.Data<>("February", 6800));
        series.getData().add(new XYChart.Data<>("March", 7200));
        series.getData().add(new XYChart.Data<>("April", 8000));
        series.getData().add(new XYChart.Data<>("May", 7800));
        series.getData().add(new XYChart.Data<>("June", 8500));
        series.getData().add(new XYChart.Data<>("July", 9000));
        series.getData().add(new XYChart.Data<>("August", 8800));
        series.getData().add(new XYChart.Data<>("September", 8200));
        series.getData().add(new XYChart.Data<>("October", 9500));
        series.getData().add(new XYChart.Data<>("November", 9700));
        series.getData().add(new XYChart.Data<>("December", 10000));

        chartsArea.getData().add(series);

        // Optional: tooltips
        for (XYChart.Data<String, Number> data : series.getData()) {
            Tooltip.install(data.getNode(), new Tooltip(
                    data.getXValue() + ": $" + data.getYValue()
            ));
        }
    }
}
