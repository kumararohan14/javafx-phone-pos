package controller.dashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public AreaChart chartsArea;
    public LineChart yearlyOrderRateChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public VBox revenueCard;
    public VBox costsCard;
    public VBox profitsCard;
    public VBox shipmentsCard;
    public VBox cardRoot;
    public Label titleLabel;
    public Label changeLabel;
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

    }
}
