package controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        Customer customer = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
        try {
            serviceFactory.save(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoadTableOnAction(ActionEvent event) {
        try {

            tblCustomers.setItems(FXCollections.observableArrayList(serviceFactory.getAll()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

