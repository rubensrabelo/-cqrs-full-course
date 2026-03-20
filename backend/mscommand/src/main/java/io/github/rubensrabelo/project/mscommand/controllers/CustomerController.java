package io.github.rubensrabelo.project.mscommand.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rubensrabelo.project.mscommand.dtos.CustomerDTO;
import io.github.rubensrabelo.project.mscommand.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.create(customerDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.update(customerDTO));
    }
}
