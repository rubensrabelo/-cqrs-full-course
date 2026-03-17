package io.github.rubensrabelo.project.mscommand.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rubensrabelo.project.mscommand.entities.BeautyProceduresEntity;

@Repository
public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity, Long> {
}
