module lk.ijse.javafx.bakerymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.javafx.bakerymanagementsystem.controller to javafx.fxml;
    exports lk.ijse.javafx.bakerymanagementsystem;
    opens lk.ijse.javafx.bakerymanagementsystem.Dto to javafx.base;

}