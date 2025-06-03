package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InventoryDto {
    private String invetoryId;
    private int stockQuantity;
    private String productId;
    private String ingredientId;

    public InventoryDto(String invetoryId, String productId, int stockQuantity) {
        this.invetoryId = invetoryId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
    }
}
