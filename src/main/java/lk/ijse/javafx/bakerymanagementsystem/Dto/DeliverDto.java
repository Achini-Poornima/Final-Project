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

    public DeliverDto(String text, String text1, String text2, String text3, String text4) {
    }
}
