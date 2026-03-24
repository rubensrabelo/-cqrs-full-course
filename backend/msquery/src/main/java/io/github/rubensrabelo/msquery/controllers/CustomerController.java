package io.github.rubensrabelo.msquery.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rubensrabelo.msquery.dtos.customers.CustomerDTO;
import io.github.rubensrabelo.msquery.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping()
    ResponseEntity<List<CustomerDTO>> listAllCustomers(){
        return ResponseEntity.ok(customerService.listAllCustomers());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<CustomerDTO>> listByNameLikeIgnoreCase(@PathVariable String name) {
        return ResponseEntity.ok(customerService.listByNameLikeIgnoreCase(name));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<List<CustomerDTO>> listByEmailLikeIgnoreCase(@PathVariable String email) {
        return ResponseEntity.ok(customerService.listByEmailLikeIgnoreCase(email));
    }
}
