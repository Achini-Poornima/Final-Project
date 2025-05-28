package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InventoryController {

    @FXML
    private AnchorPane ancIngredient;

    @FXML
    private TableColumn<?, ?> colIngredientId;

    @FXML
    private TableColumn<?, ?> colLastUpdate;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colStockQuantity;

    @FXML
    private TableColumn<?, ?> colinventoryId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblInventory;

    @FXML
    private TextField txtIngredientId;

    @FXML
    private TextField txtLastUpdate;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtStockQuantity;

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
