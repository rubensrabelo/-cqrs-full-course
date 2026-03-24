package io.github.rubensrabelo.msquery.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.rubensrabelo.msquery.dtos.beautyprocedures.BeautyProcedureDTO;

@Repository
public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
    @Query("{'name' : {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByNameIgnoreCase(String name);

    @Query("{'description' : {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByEmailLikeIgnoreCase(String description);
}