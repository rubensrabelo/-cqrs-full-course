package io.githuib.rubensrabelo.project.mssync.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
}