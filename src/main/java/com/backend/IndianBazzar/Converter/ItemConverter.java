package com.backend.IndianBazzar.Converter;

import com.backend.IndianBazzar.Model.Item;
import com.backend.IndianBazzar.Model.Product;
import com.backend.IndianBazzar.ResponseDTO.ItemResponseDTO;

public class ItemConverter {
    public static Item productToItem(Product product){
        return Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();
    }
    public static ItemResponseDTO itemToItemResponseDTO(Product product){
        return ItemResponseDTO.builder()
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
