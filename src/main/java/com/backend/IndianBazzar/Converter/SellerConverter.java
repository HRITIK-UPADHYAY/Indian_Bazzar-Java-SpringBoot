package com.backend.IndianBazzar.Converter;

import com.backend.IndianBazzar.Model.Seller;
import com.backend.IndianBazzar.RequestDTO.SellerRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.SellerResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConverter {
    public static Seller sellerRequestDTOToSeller(SellerRequestDTO sellerRequestDTO){
        return Seller.builder()
                .name(sellerRequestDTO.getName())
                .age(sellerRequestDTO.getAge())
                .email(sellerRequestDTO.getEmail())
                .mobNo(sellerRequestDTO.getMobNo())
                .panNo(sellerRequestDTO.getPanNo())
                .build();
    }
    public static SellerResponseDTO sellerToSellerResponseDTO(Seller seller){
        return SellerResponseDTO.builder()
                .SellerId(seller.getId())
                .SellerName(seller.getName())
                .SellerAge(seller.getAge())
                .SellerEmail(seller.getEmail())
                .SellerMobNo(seller.getMobNo())
                .SellerPanNo(seller.getPanNo())
                .build();
    }
}
