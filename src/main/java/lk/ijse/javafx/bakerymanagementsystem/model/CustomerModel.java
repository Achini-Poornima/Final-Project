package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.CustomerDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> list = new ArrayList<>();
        while (rst.next()) {
            CustomerDto customerDto = new CustomerDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            list.add(customerDto);
        }
        return list;
    }
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select customer_id from Customer order by customer_id desc limit 1");

        char tableChr = 'C';

        if (rst.next()){
            String lastId = rst.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNum = lastIdNumber + 1;
            String nextIdString = String.format(tableChr + "%03d", nextIdNum);
            return nextIdString;

         }
         return "C001";
    }

//    public boolean checkLogin(String username, String password) throws Exception {
//        String sql = "SELECT * FROM Users WHERE user_name = ? AND password = ?";
//        ResultSet resultSet = CrudUtil.execute(sql, username, password);
//        return resultSet.next();
//    }

     public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Customer values (?,?,?,?)",
              customerDto.getCustomerId(),
              customerDto.getName(),
              customerDto.getAddress(),
              customerDto.getContact()
      );

    }

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Customer where customer_id = ?",customerId);
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Customer set name = ?, address = ?, contact = ? where customer_id = ?",
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getContact(),
                customerDto.getCustomerId());
    }
}
