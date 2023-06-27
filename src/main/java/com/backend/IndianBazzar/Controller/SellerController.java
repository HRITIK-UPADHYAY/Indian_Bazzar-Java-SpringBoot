package com.backend.IndianBazzar.Controller;

import com.backend.IndianBazzar.RequestDTO.SellerRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.SellerResponseDTO;
import com.backend.IndianBazzar.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public SellerResponseDTO addSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        return sellerService.addSeller(sellerRequestDTO);
    }

    @GetMapping("/get/allSellers")
    public List<SellerResponseDTO> getSellers(){
        return sellerService.getSellers();
    }

    @GetMapping("/get/sellerByPanNo")
    public ResponseEntity gerSellerByPanCard(@RequestParam("panNo") String panNo){
        SellerResponseDTO sellerResponseDTO;
        try{
            sellerResponseDTO = sellerService.getSellerByPanCard(panNo);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(sellerResponseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/sellersByAge")
    public ResponseEntity getSellersByAge(@RequestParam("age") int age){
        List<SellerResponseDTO> sellers = new ArrayList<>();
        try {
            sellers = sellerService.getSellersByAge(age);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(sellers, HttpStatus.ACCEPTED);
    }
}
