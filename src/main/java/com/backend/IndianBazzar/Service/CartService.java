package com.backend.IndianBazzar.Service;

import com.backend.IndianBazzar.Enum.ProductStatus;
import com.backend.IndianBazzar.Exception.CustomerNotFoundException;
import com.backend.IndianBazzar.Exception.InsufficientQuantityException;
import com.backend.IndianBazzar.Exception.ProductNotFoundException;
import com.backend.IndianBazzar.Model.*;
import com.backend.IndianBazzar.Repository.CustomerRepository;
import com.backend.IndianBazzar.Repository.ProductRepository;
import com.backend.IndianBazzar.RequestDTO.OrderRequestDTO;
import com.backend.IndianBazzar.ResponseDTO.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JavaMailSender emailSender;

    public String addToCart(OrderRequestDTO orderRequestDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDTO.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDTO.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDTO.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! Required quantity not available");
        }

        Cart cart = customer.getCart();
        int total = cart.getCartTotal() + orderRequestDTO.getRequiredQuantity() * product.getPrice();
        cart.setCartTotal(total);

        //add item to current cart.
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDTO.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item Has Been Added Successfully";
    }

    public List<OrderResponseDTO> checkout(int customerId) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        for(Item item : cart.getItems()){
            int total = item.getRequiredQuantity() * item.getProduct().getPrice();
            int deliveryCharge = total < 500 ? 50 : 0;

            //create a order.
            Ordered order = new Ordered();
            order.setTotalCost(total);
            order.setDeliveryCharge(deliveryCharge);
            order.setCustomer(customer);
            order.getOrderedItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()- item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            //prepare response DTO
            OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(customer.getOrders().get(customer.getOrders().size()-1).getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(deliveryCharge)
                    .build();

            orderResponseDTOS.add(orderResponseDTO);
        }

        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);

        customerRepository.save(customer);

        // send an email
        String text = "Congrats your order with total value "+ totalCost +" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendavengers@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDTOS;
    }
}
