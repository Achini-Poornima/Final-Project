package lk.ijse.javafx.bakerymanagementsystem.dao;

import lk.ijse.javafx.bakerymanagementsystem.DBConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {

        Connection  connection = DbConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        for(int i = 0; i<obj.length; i++){
            pst.setObject(i + 1, obj[i]);
        }

        if(sql.startsWith("SELECT") || sql.startsWith("select")){
            ResultSet rst = pst.executeQuery();
            return (T) rst;
        }else {
            int i = pst.executeUpdate();
            boolean isSuccess = i >0;
            return (T) (Boolean) isSuccess;
        }
    }
}