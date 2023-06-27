package com.backend.IndianBazzar.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponseDTO {
    private int customerId;
    private String customerName;
    List<CardDTO> cards;
}
