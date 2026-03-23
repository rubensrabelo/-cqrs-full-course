package io.githuib.rubensrabelo.project.mssync.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.repositories.BeautyProcedureRepository;
import io.githuib.rubensrabelo.project.mssync.services.BeautyProcedureService;
import io.githuib.rubensrabelo.project.mssync.utils.SyncLogger;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository) {
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public void saveBeautyProcedure(BeautyProcedureDTO beautyProcedure) {
        try{
            SyncLogger.info("Saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureRepository.save(beautyProcedure);
        }catch (Exception e){
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
