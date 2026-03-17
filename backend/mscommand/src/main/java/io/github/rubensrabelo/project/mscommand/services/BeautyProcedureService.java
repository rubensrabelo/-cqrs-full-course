package io.github.rubensrabelo.project.mscommand.services;

import io.github.rubensrabelo.project.mscommand.dtos.BeautyProcedureDTO;

public interface BeautyProcedureService {

    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);
    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO);
    void delete(Long id);
}
