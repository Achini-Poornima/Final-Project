package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = "Achi";
        String password = "123";

        String inputUsername = txtUsername.getText();
        String inputPassword = txtPassword.getText();

        boolean isUsernameMatch = username.equals(inputUsername);
        boolean isPasswordMatch = password.equals(inputPassword);

        if(isUsernameMatch && isPasswordMatch) {
            ancLogin.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/BDashboard.fxml"));
            ancLogin.getChildren().add(parent);
        }else{
            System.out.println("Wrong");
        }
    }

}
