package com.backend.IndianBazzar.Controller;

import com.backend.IndianBazzar.RequestDTO.OrderRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.OrderResponseDTO;
import com.backend.IndianBazzar.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    private ResponseEntity addToCart(@RequestBody OrderRequestDTO orderRequestDTO){
        String response = "";
        try {
            response = cartService.addToCart(orderRequestDTO);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(@RequestParam int customerId){
        List<OrderResponseDTO> orderResponseDTOS;
        try {
            orderResponseDTOS = cartService.checkout(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDTOS, HttpStatus.ACCEPTED);
    }
}
