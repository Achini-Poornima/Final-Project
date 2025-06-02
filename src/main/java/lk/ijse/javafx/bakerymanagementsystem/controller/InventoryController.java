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
import lk.ijse.javafx.bakerymanagementsystem.Dto.InventoryDto;
import lk.ijse.javafx.bakerymanagementsystem.Dto.UserDto;
import lk.ijse.javafx.bakerymanagementsystem.model.InventoryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private AnchorPane ancIngredient;

    @FXML
    private TableColumn<InventoryDto,String> colIngredientId;

    @FXML
    private TableColumn<InventoryDto,String> colLastUpdate;

    @FXML
    private TableColumn<InventoryDto,String> colProductId;

    @FXML
    private TableColumn<InventoryDto,Integer> colStockQuantity;

    @FXML
    private TableColumn<InventoryDto,String> colinventoryId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<InventoryDto> tblInventory;

    @FXML
    private TextField txtIngredientId;

    @FXML
    private TextField txtLastUpdate;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtStockQuantity;

    private final InventoryModel inventoryModel = new InventoryModel();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          try {
              loadNextId();
              loadTable();
              resetPage();
          }catch (Exception e){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR,"Failed to load data.");
          }
    }

    private void loadTable() {
        colinventoryId.setCellValueFactory(new PropertyValueFactory<>("invetoryId"));
        colStockQuantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        colLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colIngredientId.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));

        try {
            ArrayList<InventoryDto> inventor = inventoryModel.getAllInventory();
            if (inventor != null && !inventor.isEmpty()){
                ObservableList<InventoryDto> InventoryList = FXCollections.observableArrayList(inventor);
                tblInventory.setItems(InventoryList);
            } else {
                tblInventory.setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Inventory.").show();
        }
    }

    private void resetPage() {
    }

    private void loadNextId() {
    }
}
