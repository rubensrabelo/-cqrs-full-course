package io.github.rubensrabelo.msquery.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.rubensrabelo.msquery.dtos.customers.CustomerDTO;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
    @Query("{ 'name' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByNameLikeIgnoreCase(String name);
    @Query("{ 'email' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByEmailLikeIgnoreCase(String email);
}
