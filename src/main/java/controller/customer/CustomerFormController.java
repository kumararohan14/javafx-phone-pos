package controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.impl.CustomerServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private Label lblTop;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    CustomerService serviceFactory =new CustomerServiceImpl(); //ServiceFactory.getInstance().getServiceFactoryType(ServiceType.CUSTOMER);
    //ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        //Validation if it is empty
        if (txtId.getText().trim().isEmpty() ||
                txtName.getText().trim().isEmpty() ||
                txtAddress.getText().trim().isEmpty() ||
                txtSalary.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled out.");
            return;
        }
        double salary=0;
        //Data Type Validation in salary
        try {
            if (salary < 0) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Salary must be a positive value.");
                txtSalary.clear();
                return;
            }else {
                salary = Double.parseDouble(txtSalary.getText().trim());
            }
        }catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Salary must be a valid number.");
            txtSalary.clear();
            return;
        }
        Customer customer = new Customer(txtId.getText().trim(), txtName.getText().trim(), txtAddress.getText().trim(), salary);
        try {
            serviceFactory.save(customer);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer saved successfully!");
            //clear the all field
            clearFields();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to save customer: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //show Alert
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
        // For focus to first field
        txtId.requestFocus();
    }

    @FXML
    void btnLoadTableOnAction(ActionEvent event) {
        try {
            List<Customer> customerList = serviceFactory.getAll();
            if (customerList == null){
                showAlert(Alert.AlertType.ERROR, "Data Error", "There is a Error, Refresh this.");
                return;
            }
            tblCustomers.setItems(FXCollections.observableArrayList(customerList));

            if (customerList.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "No customer records.");
            }

        } catch (SQLException e) {
            System.err.println("Database error while loading customers: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Load error","Failed to load cutomer Datar(Database issue)");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Selected customer: " + newSelection);
                txtId.setText(newSelection.getId());
                txtName.setText(newSelection.getName());
                txtAddress.setText(newSelection.getAddress());
                txtSalary.setText(String.valueOf(newSelection.getSalary()));
            }
        });
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = serviceFactory.searchById(txtId.getText());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtSalary.setText(String.valueOf(serviceFactory.getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        try {
            serviceFactory.remove(txtId.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));

        try {
            serviceFactory.update(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

