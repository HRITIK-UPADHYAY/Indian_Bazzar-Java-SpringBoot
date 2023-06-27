package com.backend.IndianBazzar.ResponseDTO;

import com.backend.IndianBazzar.Enum.ProductCategory;
import com.backend.IndianBazzar.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO {
    private String productName;

    private int productPrice;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}
