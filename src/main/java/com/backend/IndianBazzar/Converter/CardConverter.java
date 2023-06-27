package com.backend.IndianBazzar.Converter;

import com.backend.IndianBazzar.Model.Card;
import com.backend.IndianBazzar.RequestDTO.CardRequestDTO;

public class CardConverter {
    public static Card cardRequestDTOToCard(CardRequestDTO cardRequestDTO){
        return Card.builder()
                .cardNo(cardRequestDTO.getCardNo())
                .cvv(cardRequestDTO.getCvv())
                .cardType(cardRequestDTO.getCardType())
                .build();
    }
}
