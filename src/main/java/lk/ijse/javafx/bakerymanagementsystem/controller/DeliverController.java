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
import lk.ijse.javafx.bakerymanagementsystem.Dto.DeliverDto;
import lk.ijse.javafx.bakerymanagementsystem.Dto.tm.DeliverTM;
import lk.ijse.javafx.bakerymanagementsystem.model.DeliverModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DeliverController implements Initializable {
    private final DeliverModel deliverModel = new DeliverModel();
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    private final String addressPattern = "^[A-Za-z ]+$";
    private final String chargePattern = null;
    private final String datePattern = "/((0[1-9]|[12])-(0[1-9]|1[012])|30-(0[13-9]|1[012])|(31-(0[13578]|1[02])))-(19|20)/";
    private final String idPattern = null;

    @FXML
    private AnchorPane ancDeliver;

    @FXML
    private TableColumn<DeliverTM,String> colAddres;

    @FXML
    private TableColumn<DeliverTM, Date> colDate;

    @FXML
    private TableColumn<DeliverTM,Integer> colDeliverCharge;

    @FXML
    private TableColumn<DeliverTM,String> colDeliverId;

    @FXML
    private TableColumn<DeliverTM,String> colOrderId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<DeliverTM> tblDeliver;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtdeliverCharge;

    @FXML
    private TextField txtorderId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String deliverId = lblId.getText();
        String deliverAddress = txtAddress.getText();
        String deliverCharge = txtdeliverCharge.getText();
        String deliverDate = txtDate.getText();
        String orderid = txtorderId.getText();

        boolean isValidDeliverAddress = deliverAddress.matches(addressPattern);
        boolean isValidDeliverCharge = deliverCharge.matches(chargePattern);
        boolean isValidDeliverDate = deliverDate.matches(datePattern);
        boolean isVaildOrderId = orderid.matches(idPattern);



        if (){

        }

    }

    @FXML
    void btnupdateOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDeliverId.setCellValueFactory(new PropertyValueFactory<>("deliverId"));
        colAddres.setCellValueFactory(new PropertyValueFactory<>("deliverAddress"));
        colDeliverCharge.setCellValueFactory(new PropertyValueFactory<>("deliverCharge"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        try {
            resetPage();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fail to Load Data..!").show();
        }
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        loadTableData();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);

        txtAddress.setText("");
        txtDate.setText("");
        txtdeliverCharge.setText("");
        txtorderId.setText("");
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = deliverModel.getNextId();
        lblId.setText(nextId);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<DeliverDto> deliverDtoArrayList = deliverModel.getAllDeliver();
        ObservableList<DeliverTM> list = FXCollections.observableArrayList();
        for (DeliverDto deliverDto : deliverDtoArrayList){
            DeliverTM deliverTM = new DeliverTM(
                    deliverDto.getDeliverId(),
                    deliverDto.getDeliverAddress(),
                    deliverDto.getDeliverCharge(),
                    deliverDto.getDeliverDate(),
                    deliverDto.getOrderId()
            );
            list.add(deliverTM);
        }
        tblDeliver.setItems(list);

        tblDeliver.setItems(FXCollections.observableArrayList(
                deliverModel.getAllDeliver().stream().map(deliverDto ->
                        new DeliverTM(
                                deliverDto.getDeliverId(),
                                deliverDto.getDeliverAddress(),
                                deliverDto.getDeliverCharge(),
                                deliverDto.getDeliverDate(),
                                deliverDto.getOrderId()
                        )).toList()
        ));
    }
}
