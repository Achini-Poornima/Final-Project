package lk.ijse.javafx.bakerymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {

    @FXML
    private AnchorPane ancAttendance;

    @FXML
    private ComboBox<?> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colAttendanceId;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private Label colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private Label lblId;

    @FXML
    private Label lblInTime;

    @FXML
    private Label lblOutTime;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private TextField txtInTimeHour;

    @FXML
    private TextField txtInTimeMin;

    @FXML
    private TextField txtOutTimeHour;

    @FXML
    private TextField txtOutTimeMin;

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


    }

    public void onInHour(KeyEvent keyEvent) {
        String hourText = txtInTimeHour.getText();

        if (hourText.length() == 2) {
            try {
                int hour = Integer.parseInt(hourText);
                if (hour >= 0 && hour <= 23) {
                    txtInTimeMin.requestFocus();
                } else {
                    lblInTime.setText("Invalid hour (00–23)");
                }
            } catch (NumberFormatException e) {
                lblInTime.setText("Invalid hour input");
            }
        }
    }

    public void onInMin(KeyEvent keyEvent) {
        String hourText = txtInTimeHour.getText();
        String minText = txtInTimeMin.getText();

        if (hourText.length() == 2 && minText.length() == 2) {
            try {
                int hour = Integer.parseInt(hourText);
                int minute = Integer.parseInt(minText);

                if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                    lblInTime.setText(String.format("%02d:%02d", hour, minute));
                } else {
                    lblInTime.setText("Invalid time");
                }
            } catch (NumberFormatException e) {
                lblInTime.setText("Invalid input");
            }
        }
    }

    public void onOutHour(KeyEvent keyEvent) {
        String hourText = txtOutTimeHour.getText();

        if (hourText.length() == 2) {
            try {
                int hour = Integer.parseInt(hourText);
                if (hour >= 0 && hour <= 23) {
                    txtOutTimeMin.requestFocus();
                } else {
                    lblOutTime.setText("Invalid hour (00–23)");
                }
            } catch (NumberFormatException e) {
                lblOutTime.setText("Invalid hour input");
            }
        }
    }

    public void onOutMin(KeyEvent keyEvent) {
        String hourText = txtOutTimeHour.getText();
        String minText = txtOutTimeMin.getText();

        if (hourText.length() == 2 && minText.length() == 2) {
            try {
                int hour = Integer.parseInt(hourText);
                int minute = Integer.parseInt(minText);

                if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                    lblOutTime.setText(String.format("%02d:%02d", hour, minute));
                } else {
                    lblOutTime.setText("Invalid time");
                }
            } catch (NumberFormatException e) {
                lblOutTime.setText("Invalid input");
            }
        }
    }
}
