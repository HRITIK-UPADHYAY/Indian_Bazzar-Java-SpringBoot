package com.backend.IndianBazzar.Controller;

import com.backend.IndianBazzar.RequestDTO.CardRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.CardResponseDTO;
import com.backend.IndianBazzar.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDTO cardRequestDTO)  {
        CardResponseDTO cardResponseDTO;
        try {
            cardResponseDTO = cardService.addCard(cardRequestDTO);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cardResponseDTO, HttpStatus.ACCEPTED);
    }

    //remove card.

    //remove all the cards for a particular customer id.
}


