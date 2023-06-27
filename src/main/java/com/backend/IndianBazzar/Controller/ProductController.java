package com.backend.IndianBazzar.Controller;

import com.backend.IndianBazzar.Enum.ProductCategory;
import com.backend.IndianBazzar.Exception.SellerNotFoundException;
import com.backend.IndianBazzar.RequestDTO.ProductRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.ProductResponseDTO;
import com.backend.IndianBazzar.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO;

        try {
            productResponseDTO = productService.addProduct(productRequestDTO);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(productResponseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{productCategory}")
    public List<ProductResponseDTO> getProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        return productService.getProductsByCategory(productCategory);
    }
}

