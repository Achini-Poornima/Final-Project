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

    public ArrayList<CustomerDto> viewAllCustomer()throws ClassNotFoundException,SQLException{
        ResultSet rs =CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDto> customerDto = new ArrayList<>();
        while (rs.next()){
            customerDto.add(new CustomerDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }
        return customerDto;
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

     public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("insert into Customer values (?,?,?,?)",
              customerDto.getCustomerId(),
              customerDto.getName(),
              customerDto.getAddress(),
              customerDto.getContact()
      );

    }

//    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
//        ResultSet rst = CrudUtil.execute("select * from customer");
//
//        ArrayList<CustomerDto> list = new ArrayList<>();
//        while (rst.next()) {
//            CustomerDto customerDto = new CustomerDto(
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getString(4)
//            );
//            list.add(customerDto);
//        }
//        return list;
//    }

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
