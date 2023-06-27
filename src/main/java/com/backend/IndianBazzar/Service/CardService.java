package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Converter.CardConverter;
import com.backend.IndianBazzar.Exception.CustomerNotFoundException;
import com.backend.IndianBazzar.Model.Card;
import com.backend.IndianBazzar.Model.Customer;
import com.backend.IndianBazzar.Repository.CustomerRepository;
import com.backend.IndianBazzar.RequestDTO.CardRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.CardDTO;
import com.backend.IndianBazzar.ResponseDTO.CardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDTO.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        Card card = CardConverter.cardRequestDTOToCard(cardRequestDTO);
        card.setCustomer(customer);

        //add the card to current card list of customer.
        customer.getCards().add(card);

        //will save both customer and card.
        Customer updatedCustomer = customerRepository.save(customer);

        //prepare Response DTO.
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setCustomerId(updatedCustomer.getId());
        cardResponseDTO.setCustomerName(updatedCustomer.getName());

        //convert every card into CardDTO.
        List<CardDTO> cardDTOList = new ArrayList<>();
        for(Card c : updatedCustomer.getCards()){
            CardDTO cardDto = new CardDTO();
            cardDto.setCardNo(c.getCardNo());
            cardDto.setCardType(c.getCardType());

            cardDTOList.add(cardDto);
        }

        cardResponseDTO.setCards(cardDTOList);

        return cardResponseDTO;
    }
}

