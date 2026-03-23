package io.githuib.rubensrabelo.project.mssync.services;

import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customer);
}
