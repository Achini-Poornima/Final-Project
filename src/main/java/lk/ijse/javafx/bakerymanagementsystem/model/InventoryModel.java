package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.InventoryDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryModel {
    public ArrayList<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Inventory");
        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();
        while (resultSet.next()){
            inventoryDtos.add(new InventoryDto(
                    resultSet.getString("inventory_id"),
                    resultSet.getInt("stock_quantity"),
                    resultSet.getString("product_id"),
                    resultSet.getString("ingredient_id")
            ));
        }
        return inventoryDtos;
    }

    public boolean deleteInventory(String invetoryId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Inventory WHERE inventory_id = ?";
        return CrudUtil.execute(sql,invetoryId);
    }

    public boolean updateInventory(InventoryDto inventoryDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Inventory SET product_id = ?, stock_quantity = ? WHERE inventory_id = ?";
        return CrudUtil.execute(sql, inventoryDto.getProductId(), inventoryDto.getStockQuantity(), inventoryDto.getInvetoryId());
    }

    public boolean saveInventory(InventoryDto inventoryDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Inventory (inventory_id, product_id, stock_quantity) VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, inventoryDto.getInvetoryId(), inventoryDto.getProductId(), inventoryDto.getStockQuantity());
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT inventory_id FROM Inventory ORDER BY inventory_id desc limit 1");
        char tableChar = 'I';
        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public ArrayList<String> getProductIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> productIds = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT product_id FROM Product");
        while (rs.next()) {
            productIds.add(rs.getString("product_id"));
        }
        return productIds;
    }

    public ArrayList<String> getIngredientIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> ingredientIds = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT ingredient_id FROM Ingredient");
        while (rs.next()) {
            ingredientIds.add(rs.getString("ingredient_id"));
        }
        return ingredientIds;
    }
}
