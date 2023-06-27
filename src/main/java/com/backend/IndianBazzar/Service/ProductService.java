package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Converter.ProductConverter;
import com.backend.IndianBazzar.Enum.ProductCategory;
import com.backend.IndianBazzar.Exception.SellerNotFoundException;
import com.backend.IndianBazzar.Model.Product;
import com.backend.IndianBazzar.Model.Seller;
import com.backend.IndianBazzar.Repository.ProductRepository;
import com.backend.IndianBazzar.Repository.SellerRepository;
import com.backend.IndianBazzar.RequestDTO.ProductRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDTO.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotFoundException("Invalid Seller Id !! Register The Seller First");
        }


        Product product = ProductConverter.productRequestDTOToProduct(productRequestDTO);
        product.setSeller(seller);
        seller.getProducts().add(product);

        //save the both seller and product.
        Seller upadatedSeller = sellerRepository.save(seller);

        //convert the Entity into DTO.
        ProductResponseDTO productResponseDTO = ProductConverter.productToProductResponseDTO(product);
        productResponseDTO.setProductSellerId(upadatedSeller.getId());
        productResponseDTO.setProductSellerName(upadatedSeller.getName());

        return productResponseDTO;
    }

    public List<ProductResponseDTO> getProductsByCategory(ProductCategory productCategory) {
        List<Product> products = productRepository.findByProductCategory(productCategory);

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product : products){
            ProductResponseDTO productResponseDTO = ProductConverter.productToProductResponseDTO(product);
            Seller seller = product.getSeller();
            productResponseDTO.setProductSellerId(seller.getId());
            productResponseDTO.setProductSellerName(seller.getName());
            productResponseDTOS.add(productResponseDTO);
        }

        return productResponseDTOS;
    }
}
