package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class OrderDto {
    private String orderId;
    private double totalAmount;
    private LocalDateTime orderDate;
    private String paymentStatus;
    private String customerId;
    private ArrayList<OrderDetailsDto> cartList;
}