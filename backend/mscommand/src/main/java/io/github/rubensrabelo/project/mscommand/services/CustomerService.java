package io.github.rubensrabelo.project.mscommand.services;

import io.github.rubensrabelo.project.mscommand.dtos.CustomerDTO;

public interface CustomerService {
    
    CustomerDTO create(CustomerDTO customerEntity);
    CustomerDTO update(CustomerDTO customerDTO);
    void delete(Long id);
}
