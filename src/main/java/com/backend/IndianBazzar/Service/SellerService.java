package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Converter.SellerConverter;
import com.backend.IndianBazzar.Exception.SellerNotFoundException;
import com.backend.IndianBazzar.Model.Seller;
import com.backend.IndianBazzar.Repository.SellerRepository;
import com.backend.IndianBazzar.RequestDTO.SellerRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.SellerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDTO addSeller(SellerRequestDTO sellerRequestDTO){
        //convert the DTO into Entity.
        Seller seller = SellerConverter.sellerRequestDTOToSeller(sellerRequestDTO);

        //save the seller.
        Seller updatedSeller = sellerRepository.save(seller);

        //convert the DTO into Entity.
        SellerResponseDTO sellerResponseDTO = SellerConverter.sellerToSellerResponseDTO(updatedSeller);

        return sellerResponseDTO;
    }

    public List<SellerResponseDTO> getSellers(){
        List<SellerResponseDTO> sellerResponseDTOS = new ArrayList<>();
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            SellerResponseDTO sellerResponseDTO = SellerConverter.sellerToSellerResponseDTO(seller);
            sellerResponseDTOS.add(sellerResponseDTO);
        }

        return sellerResponseDTOS;
    }

    public SellerResponseDTO getSellerByPanCard(String panNo) throws SellerNotFoundException {
        Seller seller;
        try {
            seller = sellerRepository.findByPanNo(panNo);
            if(seller.equals(null)) throw new RuntimeException();
        }
        catch (Exception e){
            throw new SellerNotFoundException("No Seller Is Registerd With this Pan No");
        }

        SellerResponseDTO sellerResponseDTO = SellerConverter.sellerToSellerResponseDTO(seller);

        return sellerResponseDTO;
    }

    public List<SellerResponseDTO> getSellersByAge(int age) throws SellerNotFoundException {
        List<Seller> sellers = new ArrayList<>();
        try{
            sellers = sellerRepository.findByAge(age);
            if(sellers.isEmpty()) throw new RuntimeException();
        }
        catch (Exception e){
            throw new SellerNotFoundException("No Seller Is Found At Particular Age");
        }

        List<SellerResponseDTO> sellerResponseDTOS = new ArrayList<>();
        for(Seller seller : sellers){
            SellerResponseDTO sellerResponseDTO = SellerConverter.sellerToSellerResponseDTO(seller);
            sellerResponseDTOS.add(sellerResponseDTO);
        }

        return sellerResponseDTOS;
    }
}
