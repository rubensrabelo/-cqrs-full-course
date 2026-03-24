package io.github.rubensrabelo.msquery.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.rubensrabelo.msquery.dtos.customers.CustomerDTO;
import io.github.rubensrabelo.msquery.repositories.CustomerRepository;
import io.github.rubensrabelo.msquery.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> listAllCustomers() {
       try{
           return customerRepository.findAll();
       }catch (Exception e){
        throw new RuntimeException("Error listing all customers");
       }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try{
            return customerRepository.findByNameLikeIgnoreCase(name);
        }catch (Exception e){
            throw new RuntimeException("Error listing all customers by name");
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try{
            return customerRepository.findByEmailLikeIgnoreCase(email);
        }catch (Exception e){
            throw new RuntimeException("Error listing all customers by email");
        }
    }
}
