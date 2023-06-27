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
public class ProductResponseDTO {
    private int productSellerId;
    private String productSellerName;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}
