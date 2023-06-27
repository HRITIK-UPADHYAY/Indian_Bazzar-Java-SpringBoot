package com.backend.IndianBazzar.RequestDTO;

import com.backend.IndianBazzar.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private int sellerId;

    private String name;

    private int price;

    private int quantity;

    private ProductCategory category;
}
