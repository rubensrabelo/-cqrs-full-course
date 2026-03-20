package io.github.rubensrabelo.project.mscommand.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.rubensrabelo.project.mscommand.dtos.BeautyProcedureDTO;
import io.github.rubensrabelo.project.mscommand.entities.BeautyProceduresEntity;
import io.github.rubensrabelo.project.mscommand.repositories.BeautyProcedureRepository;
import io.github.rubensrabelo.project.mscommand.services.BeautyProcedureService;
import io.github.rubensrabelo.project.mscommand.services.BrokerService;
import io.github.rubensrabelo.project.mscommand.utils.ConverterUtil;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;
    private final BrokerService brokerService;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);
    
    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository,
            BrokerService brokerService) {
        this.beautyProcedureRepository = beautyProcedureRepository;
        this.brokerService = brokerService;
    }

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO dto) {
        BeautyProceduresEntity entity = converterUtil.convertToSource(dto);
        entity  = beautyProcedureRepository.save(entity);
        sendBeautyProceduresToQueue(entity);
        return converterUtil.convertToTarget(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> optional = beautyProcedureRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("Beauty Procedure not found");
        }
        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO dto) {
        Optional<BeautyProceduresEntity> optional = beautyProcedureRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new RuntimeException("Beauty Procedure not found");
        }
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(dto);
        beautyProceduresEntity.setAppointments(optional.get().getAppointments());
        beautyProceduresEntity.setCreatedAt(optional.get().getCreatedAt());

        BeautyProceduresEntity updatedBeautyProcedureEntity = beautyProcedureRepository.save(beautyProceduresEntity);

        sendBeautyProceduresToQueue(updatedBeautyProcedureEntity);

        return converterUtil.convertToTarget(updatedBeautyProcedureEntity);
    }

    private void sendBeautyProceduresToQueue(BeautyProceduresEntity entity){
            BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .price(entity.getPrice())
                    .build();
            brokerService.send("beautyProcedures", beautyProcedureDTO);
    }

}
