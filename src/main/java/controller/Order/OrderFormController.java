package controller.Order;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrderService;
import service.custom.impl.CustomerServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXComboBox<String> cmbCusId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private TableView tblCartItem;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtname;

    ArrayList<CartItem> cartItems = new ArrayList<>();

    CustomerService customerService = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.CUSTOMER);
    ItemService itemService = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.ITEM);
    OrderService orderService = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.ORDER);
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtStock.getText());
        double total = unitPrice * qtyOnHand;

        if(cartItems.isEmpty()){

        }
        cartItems.add(new CartItem(cmbItemCode.getValue(),txtDesc.getText(),qtyOnHand,unitPrice,total));
        tblCartItem.setItems(FXCollections.observableArrayList(cartItems));
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String orderId = txtOrderId.getText();
        Date ordeDate = new Date();
        String custId = cmbCusId.getValue().toString();

        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            orderDetails.add(
                    new OrderDetails(orderId,
                            cartItem.getCode(),
                            cartItem.getQtyOnHand(),
                            cartItem.getUnitPrice())
            );
        });
        Order order = new Order(orderId, ordeDate, custId, orderDetails);
        boolean isplaced= false;
        try {
            isplaced = orderService.placeOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isplaced){

        }
    }

    private void loadDateAndTime(){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        lblDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,e -> {
                    LocalTime now =LocalTime.now();
                    lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());

                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        System.out.println(Animation.INDEFINITE);
        timeline.play();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();
        loadCustomerId();
        loadItemCode();

        cmbCusId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            if (newValue!=null){
                setTexToValuesCustomer(newValue);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            if (newValue!=null){
                setTexToValuesItem(newValue);
            }
        });

    }

    private void setTexToValuesItem(String newValue) {
        try {
            Item item = itemService.searchById(newValue);

            txtDesc.setText(item.getDescription());
            txtStock.setText(String.valueOf(item.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTexToValuesCustomer(String newValue) {
        try {
            Customer customer = customerService.searchById(newValue);
            System.out.println(customer.getName());
            txtname.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtSalary.setText(String.valueOf(customer.getSalary()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomerId(){

        cmbCusId.setItems(FXCollections.observableArrayList(customerService.getIds()));
    }
    private void loadItemCode(){
        try {
            cmbItemCode.setItems(FXCollections.observableArrayList(itemService.getItemIds()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
