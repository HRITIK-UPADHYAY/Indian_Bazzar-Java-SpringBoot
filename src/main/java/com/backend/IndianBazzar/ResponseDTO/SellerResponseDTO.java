package com.backend.IndianBazzar.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDTO {
    private int SellerId;
    private String SellerName;
    private int SellerAge;
    private String SellerEmail;
    private String SellerMobNo;
    private String SellerPanNo;
}
