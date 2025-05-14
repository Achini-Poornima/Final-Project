package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import lk.ijse.javafx.bakerymanagementsystem.Dto.CustomerDto;
import lk.ijse.javafx.bakerymanagementsystem.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class CustomerController implements Initializable {

    @FXML
    private AnchorPane ancCustomer;

    @FXML
    private TableColumn<CustomerDto,String> colName;

    @FXML
    private TableColumn<CustomerDto,String> colAddress;

    @FXML
    private TableColumn<CustomerDto,String> colContact;

    @FXML
    private TableColumn<CustomerDto,String> colid;

    @FXML
    private Label lblId;

    @FXML
    private TableView<CustomerDto> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerDto selectedUser = (CustomerDto) tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Customer to delete.").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to delete this Customer?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = customerModel.deleteCustomer(selectedUser.getCustomerId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                    loadTable();
                    resetPage();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to delete Customer!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
          try {
              resetPage();
          }catch (Exception e){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR, "Failed to load next user ID..").show();
          }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
          if (!validDateInpts()) return;

        CustomerDto customerDto = createCustomerDtoFromInputs();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to save this Customer ?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                boolean isSaved = customerModel.saveCustomer(customerDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully.").show();
                    resetPage();
                    loadTable();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save customer.").show();
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while saving customer.").show();
            }
        }

    }

    private CustomerDto createCustomerDtoFromInputs() {
        return new CustomerDto(lblId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText());
    }

    private boolean validDateInpts() {
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String contact = txtContact.getText().trim();

        if(name.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Name is required").show();
            txtName.requestFocus();
            return false;
        }

        if (address.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Address is required").show();
            txtAddress.requestFocus();
            return false;
        }
        if (contact.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Contact is required").show();
            txtContact.requestFocus();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!validDateInpts()) return;

        CustomerDto customerDto = createCustomerDtoFromInputs();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to update this User?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isUpdated = customerModel.updateCustomer(customerDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").show();
                    loadTable();
                    resetPage();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to update User!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          try {
              loadNextId();
              loadTable();
              resetPage();
          }catch (Exception e){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR, "Failed to load data.").show();
          }
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        colid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            ArrayList<CustomerDto> customers = (ArrayList<CustomerDto>) customerModel.getAllCustomers();
            if (customers != null && !customers.isEmpty()){
                ObservableList<CustomerDto> customerList = FXCollections.observableArrayList(customers);
                tblCustomer.setItems(customerList);
            }else {
                tblCustomer.setItems(FXCollections.observableArrayList(customers));
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load customer.").show();
        }
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        tblCustomer.getSelectionModel().clearSelection();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
         lblId.setText(customerModel.getNextId());
    }

    public void setData(MouseEvent mouseEvent) {
        CustomerDto selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            lblId.setText(selectedCustomer.getCustomerId());
            txtName.setText(selectedCustomer.getName());
            txtAddress.setText(selectedCustomer.getAddress());
            txtContact.setText(selectedCustomer.getContact());
        }
    }
}
