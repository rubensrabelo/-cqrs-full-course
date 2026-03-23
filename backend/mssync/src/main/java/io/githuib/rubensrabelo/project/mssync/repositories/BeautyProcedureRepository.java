package io.githuib.rubensrabelo.project.mssync.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;

@Repository
public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
