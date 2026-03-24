package io.github.rubensrabelo.msquery.services;

import java.util.List;

import io.github.rubensrabelo.msquery.dtos.beautyprocedures.BeautyProcedureDTO;

public interface BeautyProcedureService {
    List<BeautyProcedureDTO> listAllBeautyProcedures();
    List<BeautyProcedureDTO> listByNameIgnoreCase(String name);
    List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description);
}
