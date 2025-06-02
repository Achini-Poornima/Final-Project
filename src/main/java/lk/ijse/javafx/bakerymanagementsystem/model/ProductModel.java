package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.ProductDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {
    public ArrayList<String> getAllProductIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = CrudUtil.execute(
                    "select product_id from Product"
            );
            ArrayList<String> list = new ArrayList<>();
            while (rst.next()) {
                String id = rst.getString(1);
                list.add(id);
            }
            return list;


    }
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT item_id FROM item ORDER BY item_id DESC limit 1");
        char tableChar = 'P';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public ProductDto findById(String selectedId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Product WHERE product_id=?";
        ResultSet resultset = CrudUtil.execute(sql, selectedId);
        if (resultset.next()) {
            return new ProductDto(
                    resultset.getString("product_id"),
                    resultset.getString("name"),
                    resultset.getInt("stock_quantity"),
                    resultset.getDouble("price")
            );
        }
        return null;
    }
}
