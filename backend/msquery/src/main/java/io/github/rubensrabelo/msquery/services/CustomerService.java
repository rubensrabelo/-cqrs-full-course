package io.github.rubensrabelo.msquery.services;

import java.util.List;

import io.github.rubensrabelo.msquery.dtos.customers.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> listAllCustomers();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);
}
