package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SalaryController {

    @FXML
    private AnchorPane ancSalary;

    @FXML
    private TableColumn<?, ?> colBasicSalary;

    @FXML
    private TableColumn<?, ?> colBonus;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colNetSalary;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblSalary;

    @FXML
    private TextField txtBasicSalary;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtNetSalary;

    @FXML
    private TextField txtPaymentDate;

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

    @FXML
    void onSetData(MouseEvent event) {

    }

}
