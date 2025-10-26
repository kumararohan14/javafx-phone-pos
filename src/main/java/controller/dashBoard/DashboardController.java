package controller.dashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

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
}
