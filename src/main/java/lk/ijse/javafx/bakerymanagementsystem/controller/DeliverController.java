package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeliverController {

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

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
