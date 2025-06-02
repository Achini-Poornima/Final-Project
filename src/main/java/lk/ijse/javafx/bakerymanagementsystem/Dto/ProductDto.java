package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String name;
    private BigDecimal price;
    private int stockQuantity;
    private String supplierId;

    public ProductDto(String productId, String name, int stockQuantity, double price) {
        this.productId = productId;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = BigDecimal.valueOf(price);
    }
}