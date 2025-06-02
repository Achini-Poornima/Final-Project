package lk.ijse.javafx.bakerymanagementsystem.Dto.TM;

import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class CartTM {

    private String productId;
    private String productName;
    private int quantity;
    private double price;
    public CartTM(String productId, String productName, int quantity, double price){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}