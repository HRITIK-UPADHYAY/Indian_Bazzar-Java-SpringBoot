package com.backend.IndianBazzar.Converter;

import com.backend.IndianBazzar.Model.Customer;
import com.backend.IndianBazzar.RequestDTO.CustomerRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.CustomerResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {
    public static Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO){
        return Customer.builder()
                .name(customerRequestDTO.getName())
                .age(customerRequestDTO.getAge())
                .email(customerRequestDTO.getEmail())
                .mobNo(customerRequestDTO.getMobNo())
                .build();
    }

    public static CustomerResponseDTO customerToCustomerResonseDTO(Customer customer){
        return CustomerResponseDTO.builder()
                .customerId(customer.getId())
                .customerName(customer.getName())
                .customerAge(customer.getAge())
                .customerMobNo(customer.getMobNo())
                .customerEmail(customer.getEmail())
                .build();
    }
}
