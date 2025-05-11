package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane ancMainContainer;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
navigateTo("/view/Customer.fxml");
    }

    @FXML
    void btnDeliverOnAction(ActionEvent event) {
        navigateTo("/view/Deliver.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigateTo("/view/Employee.fxml");
    }

    @FXML
    void btnExpensesOnAction(ActionEvent event) {
        navigateTo("/view/Expenses.fxml");
    }

    @FXML
    void btnIngrediantOnAction(ActionEvent event) {
        navigateTo("/view/Ingredient.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigateTo("/view/Inventory.fxml");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        navigateTo("/view/Order.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void btnProductOnAction(ActionEvent event) {
        navigateTo("/view/Product.fxml");
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) {
        navigateTo("/view/Salary.fxml");
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        navigateTo("/view/Supplier.fxml");
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {
        navigateTo("/view/User.fxml");
    }
    private void navigateTo(String path) {
        try{

            ancMainContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(ancMainContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainContainer.heightProperty());
            ancMainContainer.getChildren().add(anchorPane);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,"Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

}

