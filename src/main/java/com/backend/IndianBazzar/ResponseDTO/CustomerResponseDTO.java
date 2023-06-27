package com.backend.IndianBazzar.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private int customerId;
    private String customerName;
    private int customerAge;
    private String customerMobNo;
    private String customerEmail;
}
