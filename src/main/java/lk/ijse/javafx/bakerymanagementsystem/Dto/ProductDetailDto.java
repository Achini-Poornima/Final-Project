package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailDto {
    private String productId;
    private double quantityUsed;
    private String ingredientId;
}