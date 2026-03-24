package io.github.rubensrabelo.msquery.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.rubensrabelo.msquery.dtos.beautyprocedures.BeautyProcedureDTO;
import io.github.rubensrabelo.msquery.repositories.BeautyProcedureRepository;
import io.github.rubensrabelo.msquery.services.BeautyProcedureService;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository){
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedures() {
        try{
            return beautyProcedureRepository.findAll();
        }catch (Exception e)
        {
            throw new RuntimeException("Error listening all beauty procedures");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try{
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        }catch (Exception e){
            throw new RuntimeException("Error listing all beauty procedures by name");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try{
            return beautyProcedureRepository.findByEmailLikeIgnoreCase(description);
        }catch (Exception e){
            throw new RuntimeException("Error listing all beauty procedures by description");
        }
    }
}
