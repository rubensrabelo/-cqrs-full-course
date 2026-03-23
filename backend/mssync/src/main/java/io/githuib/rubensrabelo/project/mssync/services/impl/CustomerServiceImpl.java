package io.githuib.rubensrabelo.project.mssync.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;
import io.githuib.rubensrabelo.project.mssync.repositories.CustomerRepository;
import io.githuib.rubensrabelo.project.mssync.services.CustomerService;
import io.githuib.rubensrabelo.project.mssync.utils.SyncLogger;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerDTO customer) {
        try{
            SyncLogger.info("Saving customer: " + customer.getId());
            customerRepository.save(customer);
        }catch (Exception e){
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
