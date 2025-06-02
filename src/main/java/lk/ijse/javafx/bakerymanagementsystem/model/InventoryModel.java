package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.InventoryDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryModel {
    public ArrayList<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FRON Inventory");
        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();
        while (resultSet.next()){
            inventoryDtos.add(new InventoryDto(
                    resultSet.getString("inventory_id"),
                    resultSet.getInt("stock_quantity"),
                    resultSet.getString("last_update"),
                    resultSet.getString("product_id"),
                    resultSet.getString("ingreient_id")
            ));
        }
        return inventoryDtos;
    }
}
