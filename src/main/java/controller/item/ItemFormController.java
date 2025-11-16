package controller.item;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import util.DBConnection;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public TableColumn colItemDescription;
    public TableColumn colItemId;
    public TableColumn colCategory;
    public TableColumn colBrand;
    public TableColumn colSupplier;
    public TableColumn colCost;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colReorderLevel;
    public TableColumn colLocation;
    public TableColumn colProductionDate;
    public TableColumn colWarranty;
    public TableColumn colStatus;
    public JFXTextField txtWarranty;
    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private JFXComboBox<String> cmbSupplierId;
    @FXML
    private DatePicker dateProduction;


    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtCostPrice;

    @FXML
    private JFXTextArea txtDesc;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtReOrdeLevel;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private TableView tblItems;


    ItemService serviceFactory = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.ITEM);
    //save the item to database
    @FXML
    void btnAddItemOnAction(ActionEvent event)  {

        boolean save = false;
        String itemCode = txtCode.getText();
        try {

            Item item = new Item(
                    txtCode.getText(),
                    txtDesc.getText(),
                    cmbCategory.getValue(),
                    txtBrand.getText(),
                    cmbSupplierId.getValue(),
                    Double.parseDouble(txtCostPrice.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    Integer.parseInt(txtReOrdeLevel.getText()),
                    txtLocation.getText(),
                    dateProduction.getValue(),
                    txtWarranty.getText(),
                    cmbStatus.getValue()
            );

            save = serviceFactory.save(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (save) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Transaction Successful");
            successAlert.setHeaderText("✅ Success: Item " + itemCode + " Saved");
            successAlert.setContentText("The new item has been successfully added to the system inventory.");
            successAlert.showAndWait();
            loadTable();

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Operation Failed");
            errorAlert.setHeaderText("❌ Database Error: Failed to Save Item");
            errorAlert.setContentText(
                    "Item " + itemCode + " could not be saved."
            );
            errorAlert.showAndWait();
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        try {
            String itemCode = txtCode.getText();
            boolean remove = serviceFactory.remove(txtCode.getText());
            if (remove) {
                // --- SUCCESS ALERT ---
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Removal Successful");
                successAlert.setHeaderText("✅ Success: Item " + itemCode + " Deleted");
                successAlert.setContentText("The item has been permanently removed from the inventory system.");
                successAlert.showAndWait();
                loadTable();
            } else {
                // --- FAILURE ALERT ---
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Removal Error");
                errorAlert.setHeaderText("❌ Operation Failed: Cannot Remove Item");
                errorAlert.setContentText(
                        "Item " + itemCode + " could not be removed. This usually happens if "
                );
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        try {
            Item item = serviceFactory.searchById(txtCode.getText());
            setDataToFields(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        boolean save = false;
        String itemCode;
        try {
             itemCode = txtCode.getText();
            Item item = new Item(
                    txtCode.getText(),
                    txtDesc.getText(),
                    cmbCategory.getValue(),
                    txtBrand.getText(),
                    cmbSupplierId.getValue(),
                    Double.parseDouble(txtCostPrice.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    Integer.parseInt(txtReOrdeLevel.getText()),
                    txtLocation.getText(),
                    dateProduction.getValue(),
                    txtWarranty.getText(),
                    cmbStatus.getValue()
            );

            save = serviceFactory.update(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (save) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Transaction Successful");
            successAlert.setHeaderText("✅ Success: Item " + itemCode + " Saved");
            successAlert.setContentText("The new item has been successfully added to the system inventory.");
            successAlert.showAndWait();
            loadTable();

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Operation Failed");
            errorAlert.setHeaderText("❌ Database Error: Failed to Save Item");
            errorAlert.setContentText(
                    "Item " + itemCode + " could not be saved."
            );
            errorAlert.showAndWait();
        }
    }

    public void btnLoadItemOnAction(ActionEvent actionEvent) {
        try {
            List<Item> all = serviceFactory.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setDataToFields(Item item) {
        txtCode.setText(item.getCode());
        txtDesc.setText(item.getDescription());
        cmbCategory.setValue(item.getCategory());
        txtBrand.setText(item.getBrand());
        cmbSupplierId.setValue(item.getSupplierId());
        txtCostPrice.setText(String.valueOf(item.getCostPrice()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtReOrdeLevel.setText(String.valueOf(item.getReorderLevel()));
        txtLocation.setText(item.getLocation());
        dateProduction.setValue(item.getProductionDate());
        txtWarranty.setText(item.getWarrantyPeriod());
        cmbStatus.setValue(item.getStatus());

    }
    private void loadTable(){
        try {
            tblItems.setItems(FXCollections.observableArrayList(serviceFactory.getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            colItemId.setCellValueFactory(new PropertyValueFactory<>("code"));
            colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
            colCost.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colReorderLevel.setCellValueFactory(new PropertyValueFactory<>("reorderLevel"));
            colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            colProductionDate.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
            colWarranty.setCellValueFactory(new PropertyValueFactory<>("warrantyPeriod"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            loadTable();
            //set status for comboBox
            cmbStatus.setItems(FXCollections.observableArrayList("Active","Deactive"));

            // row selection
            tblItems.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    setDataToFields((Item) newSelection);
                }
            });
        try {
            cmbSupplierId.setItems(FXCollections.observableArrayList(serviceFactory.getSupplierIds()));
            cmbCategory.getItems().addAll(FXCollections.observableArrayList(serviceFactory.getItemCategories()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnReportItemOnAction(ActionEvent actionEvent) {
        try{
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/item/Flower_Landscape.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Item_report_2025.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        }catch (JRException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
