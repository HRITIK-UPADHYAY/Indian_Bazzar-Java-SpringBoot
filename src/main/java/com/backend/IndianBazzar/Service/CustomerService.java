package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Converter.CustomerConverter;
import com.backend.IndianBazzar.Exception.CustomerNotFoundException;
import com.backend.IndianBazzar.Model.Cart;
import com.backend.IndianBazzar.Model.Customer;
import com.backend.IndianBazzar.Repository.CustomerRepository;
import com.backend.IndianBazzar.RequestDTO.CustomerRequestDTO;
import com.backend.IndianBazzar.RequestDTO.CustomerUpdateEmailRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer = CustomerConverter.customerRequestDTOToCustomer(customerRequestDTO);

        //allocate a cart to customer.
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart in customer.
        customer.setCart(cart);

        //save the customer and cart.
        Customer updatedCustomer = customerRepository.save(customer);

        CustomerResponseDTO customerResponseDTO = CustomerConverter.customerToCustomerResonseDTO(customer);

        return customerResponseDTO;
    }

    public CustomerResponseDTO getCustomerById(int id) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id, No Customer Is Present With This Id");
        }

        CustomerResponseDTO customerResponseDTO = CustomerConverter.customerToCustomerResonseDTO(customer);

        return customerResponseDTO;
    }

    public List<CustomerResponseDTO> getCustomers(){
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();

        for(Customer customer : customers){
            CustomerResponseDTO customerResponseDTO = CustomerConverter.customerToCustomerResonseDTO(customer);
            customerResponseDTOS.add(customerResponseDTO);
        }

        return customerResponseDTOS;
    }

    public CustomerResponseDTO getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findByEmail(email);
            if(customer.equals(null)) throw new RuntimeException();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer Is Not Registered With This Email");
        }

        return CustomerConverter.customerToCustomerResonseDTO(customer);
    }

    public CustomerResponseDTO updateCustomerEmail(CustomerUpdateEmailRequestDTO customerUpdateEmailRequestDTO) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findById(customerUpdateEmailRequestDTO.getId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        customer.setEmail(customerUpdateEmailRequestDTO.getEmail());

        return CustomerConverter.customerToCustomerResonseDTO(customer);
    }
}
