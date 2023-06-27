package com.backend.IndianBazzar.Controller;

import com.backend.IndianBazzar.Exception.CustomerNotFoundException;
import com.backend.IndianBazzar.RequestDTO.CustomerRequestDTO;
import com.backend.IndianBazzar.RequestDTO.CustomerUpdateEmailRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.CustomerResponseDTO;
import com.backend.IndianBazzar.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDTO addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.addCustomer(customerRequestDTO);
    }

    //get customer by id.
    @GetMapping("/get/customerById")
    public ResponseEntity getCustomerById(@RequestParam("id") int id){
        CustomerResponseDTO customerResponseDTO;
        try {
            customerResponseDTO = customerService.getCustomerById(id);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(customerResponseDTO, HttpStatus.ACCEPTED);
    }

    //view all customers.
    @GetMapping("/get/customers")
    public List<CustomerResponseDTO> getCustomers(){
        return customerService.getCustomers();
    }

    //get a customer by email.
    @GetMapping("/get/customerByEmail")
    public ResponseEntity getCustomerByEmail(@RequestParam("email") String email){
        CustomerResponseDTO customerResponseDTO;

        try {
            customerResponseDTO = customerService.getCustomerByEmail(email);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(customerResponseDTO, HttpStatus.ACCEPTED);
    }

    //update customer.
    @PutMapping("/update/customerEmail")
    public ResponseEntity updateCustomerEmail(@RequestBody CustomerUpdateEmailRequestDTO customerUpdateEmailRequestDTO){
        CustomerResponseDTO customerResponseDTO;
        try{
            customerResponseDTO = customerService.updateCustomerEmail(customerUpdateEmailRequestDTO);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(customerResponseDTO, HttpStatus.ACCEPTED);
    }

    //delete customer by id.
}

