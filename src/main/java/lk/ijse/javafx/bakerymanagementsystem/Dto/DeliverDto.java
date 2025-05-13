package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.util.Date;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliverDto {
    private String deliverId;
    private String deliverAddress;
    private int deliverCharge;
    private String deliverDate;
    private String orderId;


}
