package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class OrderDetailsDto {
    private String orderId;
    private double price;
    private int quantity;
    private String productId;
}