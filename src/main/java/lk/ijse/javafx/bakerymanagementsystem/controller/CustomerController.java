package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.javafx.bakerymanagementsystem.Dto.CustomerDto;
import lk.ijse.javafx.bakerymanagementsystem.Dto.tm.CustomerTM;
import lk.ijse.javafx.bakerymanagementsystem.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private final CustomerModel customerModel = new CustomerModel();
    private final String namePattern = "^[A-Za-z ]+$";
    private final String phonePattern = "^[0-9]{10}$";

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    @FXML
    private AnchorPane ancCustomer;

    @FXML
    private TableColumn<CustomerTM,String> colAddress;

    @FXML
    private TableColumn<CustomerTM,String> colName;

    @FXML
    private TableColumn<CustomerTM,String> colPhone;

    @FXML
    private TableColumn<CustomerTM,String> colId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are your sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if(response.isPresent() && response.get() == ButtonType.YES){
            try {
                String customerId = lblId.getText();
                boolean isDeleted = customerModel.deleteCustomer(customerId);

                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully..").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer.").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer..!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         resetPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #fff;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #fff;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #fff;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: red;");
        }


        CustomerDto customerDto = new CustomerDto(id,address,name,phone);

        if (isValidPhone&&isValidName){
           try {
            boolean isSave = customerModel.saveCustomer(customerDto);
            if (isSave) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully..!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save customer..!").show();
           }
        }
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
            loadNextId();
            loadTableData();

            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);

            txtName.setText("");
            txtAddress.setText("");
            txtPhone.setText("");

            tblCustomer.getSelectionModel().clearSelection();

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = customerModel.getNextId();
        lblId.setText(nextId);
    }

    private void loadTableData() {
        try {
            List<CustomerDto> customerList = CustomerModel.getAllCustomers();
            ObservableList<CustomerTM> customerTMs = FXCollections.observableArrayList();
            for (CustomerDto dto : customerList) {
                customerTMs.add(new CustomerTM(
                        dto.getCustomerId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getContact()
                ));
            }
            tblCustomer.setItems(customerTMs);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers.").show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(phonePattern);


        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #fff;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #fff;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #fff;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidName) {
            txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: red;");
        }



        CustomerDto customerDto = new CustomerDto(id,address,name,phone);

        if (isValidPhone && isValidName){
            try {
                boolean isUpdate = customerModel.updateCustomer(customerDto);
                if (isUpdate) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated successfully..!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to Update customer..!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to Update customer..!").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        try {
            resetPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }


    public void txtNameChange(KeyEvent keyEvent) {
        String name = txtName.getText();

        boolean isValidName = name.matches(namePattern);

        if(!isValidName){
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
        }else {
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: #fff;");
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            lblId.setText(selectedItem.getCustomerId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtPhone.setText(selectedItem.getCustomerId());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

}



