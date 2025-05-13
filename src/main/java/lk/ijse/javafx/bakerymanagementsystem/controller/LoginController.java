package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.javafx.bakerymanagementsystem.model.UserModel;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String inputUsername = txtUsername.getText();
        String inputPassword = txtPassword.getText();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter both username and password.", ButtonType.OK).show();
            return;
        }

        try {
            UserModel userModel = new UserModel();
            boolean isValid = userModel.checkLogin(inputUsername, inputPassword);

            if (isValid) {
                navigateTo("/view/BDashboard.fxml");
            } else {
                new Alert(Alert.AlertType.WARNING, "Invalid username or password!", ButtonType.OK).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Login failed. Error: " + e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    public void navigateTo(String path) {
        try {
            ancLogin.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(ancLogin.widthProperty());
            anchorPane.prefHeightProperty().bind(ancLogin.heightProperty());
            ancLogin.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }
}