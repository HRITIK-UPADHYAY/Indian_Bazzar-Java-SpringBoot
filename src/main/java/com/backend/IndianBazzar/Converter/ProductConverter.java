package com.backend.IndianBazzar.Converter;

import com.backend.IndianBazzar.Enum.ProductStatus;
import com.backend.IndianBazzar.Model.Product;
import com.backend.IndianBazzar.RequestDTO.ProductRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.ProductResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConverter {
    public static Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO){
        return Product.builder()
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .productCategory(productRequestDTO.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDTO productToProductResponseDTO(Product product){
        return ProductResponseDTO.builder()
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productQuantity(product.getQuantity())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
