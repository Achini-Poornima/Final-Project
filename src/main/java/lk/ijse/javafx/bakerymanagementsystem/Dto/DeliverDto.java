package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DeliverDto {
    private String deliverId;
    private String deliverAddress;
    private double deliverCharge;
    private LocalDateTime deliverDate;
    private String orderId;

    public DeliverDto(String deliverId, String deliverAddress, double deliverCharge, String deliverDate, String orderId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.deliverId = deliverId;
        this.deliverAddress = deliverAddress;
        this.deliverCharge = deliverCharge;
        this.deliverDate = LocalDateTime.parse(deliverDate, formatter);
        this.orderId = orderId;
    }
}
