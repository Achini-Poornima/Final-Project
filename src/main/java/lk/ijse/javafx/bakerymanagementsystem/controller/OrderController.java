package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.javafx.bakerymanagementsystem.Dto.ProductDto;
import lk.ijse.javafx.bakerymanagementsystem.Dto.TM.CartTM;
import lk.ijse.javafx.bakerymanagementsystem.model.CustomerModel;
import lk.ijse.javafx.bakerymanagementsystem.model.OrderModel;
import lk.ijse.javafx.bakerymanagementsystem.model.ProductModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

        @FXML
        private AnchorPane ancOrder;

        @FXML
        private ComboBox<String> cmbCustomerId;

        @FXML
        private ComboBox<String> cmbProductId;

        @FXML
        private ComboBox<String> cmbPay;

        @FXML
        private TableColumn<?, ?> colAction;

        @FXML
        private TableColumn<CartTM, String> colItemId;

        @FXML
        private TableColumn<CartTM, String> colName;

        @FXML
        private TableColumn<CartTM, Double> colPrice;

        @FXML
        private TableColumn<CartTM, Integer> colQuantity;

        @FXML
        private TableColumn<CartTM, Double> colTotal;

        @FXML
        private Label lblCustomerName;

        @FXML
        private Label lblItemName;

        @FXML
        private Label lblItemPrice;

        @FXML
        private Label lblItemQty;

        @FXML
        private Label lblOrderId;

        @FXML
        private Label orderDate;

        @FXML
        private TableView<CartTM> tblCart;

        @FXML
        private TextField txtAddToCartQty;

        private final OrderModel orderModel =new OrderModel();
        private final ProductModel productModel = new ProductModel();

        private final CustomerModel customerModel = new CustomerModel();

        private final ObservableList<CartTM> cartData = FXCollections.observableArrayList();

        @FXML
        void btnAddToCartOnAction(ActionEvent event) {
                String selectedProductIds = cmbProductId.getValue();
                String cartQtyString = txtAddToCartQty.getText();

                if (selectedProductIds == null){
                        new Alert(Alert.AlertType.WARNING,"Please select item..").show();
                        return;
                }

                if (!cartQtyString.matches("^[0-9]+$")) {
                        new Alert(Alert.AlertType.WARNING, "Please enter valid quantity..!").show();
                        return;
                }
                int cartQty = Integer.parseInt(cartQtyString);
                int itemStockQty = Integer.parseInt(lblItemQty.getText());

                if (itemStockQty < cartQty){
                        new Alert(Alert.AlertType.WARNING, "Not enough stock quantity available..!").show();
                        return;
                }
                String itemName = lblItemName.getText();
                double itemUnitPrice = Double.parseDouble(lblItemPrice.getText());

                for (CartTM cartTM : cartData) {
                        if (cartTM.getProductId().equals(selectedProductIds)) {
                                int newQty = cartTM.getCartQty() + cartQty;
                                if (itemStockQty < newQty) {
                                        new Alert(
                                                Alert.AlertType.WARNING,
                                                "Not enough item quantity..!"
                                        ).show();
                                        return;
                                }
                                cartTM.setQuantity(newQty);
                                cartTM.setTotal(newQty * itemUnitPrice);
                        }
                }
        }

        @FXML
        void btnPlaceOrderOnAction(ActionEvent event) {

        }

        @FXML
        void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
             resetPage();
        }


        @FXML
        void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
                String selectedId = cmbProductId.getSelectionModel().getSelectedItem();
                ProductDto productDto = productModel.findById(selectedId);

                if (productDto != null) {
                        lblItemName.setText(productDto.getName());
                        lblItemQty.setText(String.valueOf(productDto.getStockQuantity()));
                        lblItemPrice.setText(String.valueOf(productDto.getPrice()));
                } else {
                        lblItemName.setText("");
                        lblItemQty.setText("");
                        lblItemPrice.setText("");
                }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
                colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
                colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

                tblCart.setItems(cartData);

                cmbPay.setItems(FXCollections.observableArrayList("Cash","Card"));

                try {
                        resetPage();
                } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(
                                Alert.AlertType.ERROR, "Fail to load data..!"
                        ).show();
                }        }

        private void resetPage() throws SQLException, ClassNotFoundException {
                lblOrderId.setText(orderModel.getNextOrderId());
                orderDate.setText(LocalDate.now().toString());
                loadCustomerIds();
                loadItemIds();
        }

        private void loadItemIds() throws SQLException, ClassNotFoundException {
                cmbProductId.setItems(
                        FXCollections.observableArrayList(
                                productModel.getAllProductIds()
                        )
                );
        }

        private void loadCustomerIds() throws SQLException, ClassNotFoundException {
                cmbCustomerId.setItems(
                FXCollections.observableArrayList(
                        customerModel.getAllCustomerIds()
               )
      );
        }

        @FXML
        void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
                String selectedId = cmbCustomerId.getSelectionModel().getSelectedItem();
                String name = customerModel.findNameById(selectedId);
                lblCustomerName.setText(name);
        }
}
