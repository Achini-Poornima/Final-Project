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
import lk.ijse.javafx.bakerymanagementsystem.Dto.DeliverDto;
import lk.ijse.javafx.bakerymanagementsystem.model.DeliverModel;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeliverController implements Initializable {

    @FXML
    private AnchorPane ancDeliver;

    @FXML
    private TableColumn<?, ?> colDeliverAddress;

    @FXML
    private TableColumn<?, ?> colDeliverCharge;

    @FXML
    private TableColumn<?, ?> colDeliverDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblDeliver;

    @FXML
    private TextField txtDeliverAddress;

    @FXML
    private TextField txtDeliverCharge;

    @FXML
    private TextField txtDeliverDate;

    @FXML
    private TextField txtOrderId;

    private final DeliverModel deliverModel = new DeliverModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        DeliverDto selectedDeliver = (DeliverDto) tblDeliver.getSelectionModel().getSelectedItem();
        if (selectedDeliver == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user to delete.").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to delete this User?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = deliverModel.deleteDeliver(selectedDeliver.getDeliverId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
                    loadTable();
                    resetPage();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to delete User!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        try {
            resetPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load next Deliver ID.").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!validDataInputs()) return;

        DeliverDto deliverDto = createDeliverDtoFromInputs();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("are you sure you want to save this Deliver..");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                boolean isSaved = deliverModel.saveDeliver(deliverDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Deliver saved successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Deliver").show();
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Unexpected error occurred while adding the user!").show();
            }
        }
    }

    private DeliverDto createDeliverDtoFromInputs() {
    }

    private boolean validDataInputs() {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!validDataInputs()) return;

        DeliverDto deliverDto = createDeliverDtoFromInputs();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to update this User?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isUpdated = deliverModel.updaDeliver(deliverDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Deliver updated successfully!").show();
                    loadTable();
                    resetPage();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to update Deliver!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
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
            new Alert(Alert.AlertType.ERROR,"Fail to load data..").show();
        }

    }

    private void loadTable() {
        colid.setCellValueFactory(new PropertyValueFactory<>("deliverId"));
        colDeliverAddress.setCellValueFactory(new PropertyValueFactory<>("deliverAddress"));
        colDeliverCharge.setCellValueFactory(new PropertyValueFactory<>("deliverCharge"));
        colDeliverDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderid"));

        try {
            ArrayList<DeliverDto> deliver = deliverModel.getAllUsers();
            if (deliver != null && !deliver.isEmpty()) {
                ObservableList<DeliverDto> deliverList = FXCollections.observableArrayList(deliver);
                tblDeliver.setItems(deliverList);
            } else {
                tblDeliver.setItems(FXCollections.observableArrayList());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load users.").show();
        }
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        txtDeliverAddress.clear();
        txtDeliverCharge.clear();
        txtDeliverDate.clear();
        txtOrderId.clear();
        tblDeliver.getSelectionModel().clearSelection();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        lblId.setText(deliverModel.getNextId());
    }

    public void setData(MouseEvent mouseEvent) {
        DeliverDto deliverDto = (DeliverDto) tblDeliver.getSelectionModel().getSelectedItem();
        if (deliverDto != null){
            lblId.setText(deliverDto.getDeliverId());
            txtDeliverAddress.setText(deliverDto.getDeliverAddress());
            txtDeliverCharge.setText(String.valueOf(deliverDto.getDeliverCharge()));
            txtDeliverDate.setText(deliverDto.getDeliverDate());
            txtOrderId.setText(deliverDto.getOrderId());
        }
    }
}
