package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Converter.ItemConverter;
import com.backend.IndianBazzar.Exception.ProductNotFoundException;
import com.backend.IndianBazzar.Model.Item;
import com.backend.IndianBazzar.Model.Product;
import com.backend.IndianBazzar.Repository.ProductRepository;
import com.backend.IndianBazzar.ResponseDTO.ItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDTO viewItem(int productId) throws ProductNotFoundException {
        Product product;
        try {
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Sorry!! Invalid Product Id");
        }

        //create a item.
        Item item = ItemConverter.productToItem(product);

        //map item for product.
        product.setItem(item);

        //save both item and product.
        productRepository.save(product);

        //prepare the responseDTO.
        ItemResponseDTO itemResponseDTO = ItemConverter.itemToItemResponseDTO(product);

        return  itemResponseDTO;
    }
}

