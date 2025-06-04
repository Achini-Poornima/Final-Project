package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import lk.ijse.javafx.bakerymanagementsystem.Dto.InventoryDto;
import lk.ijse.javafx.bakerymanagementsystem.model.InventoryModel;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    public ComboBox<String> txtProductId;
    public ComboBox<String> txtIngredientId;

    @FXML
    private TableColumn<InventoryDto,String> colIngredientId;

    @FXML
    private TableColumn<InventoryDto,String> colProductId;

    @FXML
    private TableColumn<InventoryDto,Integer> colStockQuantity;

    @FXML
    private TableColumn<InventoryDto,String> colInventoryId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<InventoryDto> tblInventory;

    @FXML
    private TextField txtStockQuantity;

    private final InventoryModel inventoryModel = new InventoryModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        InventoryDto selectedInventory = tblInventory.getSelectionModel().getSelectedItem();
        if (selectedInventory == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user to delete.").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initStyle(StageStyle.UNDECORATED);
        confirmationAlert.setContentText("Are you sure you want to delete this Inventory Details?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = inventoryModel.deleteInventory(selectedInventory.getInventoryId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Inventory Details deleted successfully!").show();
                    loadTable();
                    resetPage();
                    loadNextId();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to delete Inventory Details!").show();
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
            new Alert(Alert.AlertType.ERROR, "Failed to reset page.").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!validDateInputs()) return;

        InventoryDto inventoryDto = createInventoryDtoFromInputs();

        try {
            boolean isAdded = inventoryModel.saveInventory(inventoryDto);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Inventory Details added successfully!").show();
                loadTable();
                resetPage();
                loadNextId();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to add Inventory Details!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
        }

    }

    private boolean validDateInputs() {
        if (txtStockQuantity.getText().isEmpty() || txtProductId.getValue() == null || txtIngredientId.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill all the fields.").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!validDateInputs()) return;

        InventoryDto inventoryDto = createInventoryDtoFromInputs();

        try {
            boolean isUpdated = inventoryModel.updateInventory(inventoryDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Inventory Details updated successfully!").show();
                loadTable();
                resetPage();
                loadNextId();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to update Inventory Details!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
        }
    }

    private InventoryDto createInventoryDtoFromInputs() {
        return new InventoryDto(
                lblId.getText(),
                Integer.parseInt(txtStockQuantity.getText()),
                txtProductId.getValue(),
                txtIngredientId.getValue());
    }

        @FXML
    void onSetData(MouseEvent event) {
        InventoryDto inventoryDto = tblInventory.getSelectionModel().getSelectedItem();
        if (inventoryDto != null){
            lblId.setText(inventoryDto.getInventoryId());
            txtStockQuantity.setText(String.valueOf(inventoryDto.getStockQuantity()));
            txtProductId.setValue(inventoryDto.getProductId());
            txtIngredientId.setValue(inventoryDto.getIngredientId());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          try {
              loadNextId();
              loadTable();
              resetPage();
              loadProductIds();
              loadIngredientIds();
          }catch (Exception e){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR,"Failed to load data.").show();
          }
    }

    private void loadIngredientIds() {
        try {
            ArrayList<String> ingredientIds = inventoryModel.getIngredientIds();
            ObservableList<String> observableIngredientIds = FXCollections.observableArrayList(ingredientIds);
            txtIngredientId.setItems(observableIngredientIds);
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load today's order IDs.").show();
        }
    }

    private void loadProductIds() {
        try {
            ArrayList<String> productIds = inventoryModel.getProductIds();
            ObservableList<String> observableProductIds = FXCollections.observableArrayList(productIds);
            txtProductId.setItems(observableProductIds);
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load today's order IDs.").show();
        }
    }

    private void loadTable() {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colStockQuantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
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

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        txtStockQuantity.clear();
        txtProductId.setValue(null);
        txtIngredientId.setValue(null);
        tblInventory.getSelectionModel().clearSelection();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        lblId.setText(inventoryModel.getNextId());
    }
}