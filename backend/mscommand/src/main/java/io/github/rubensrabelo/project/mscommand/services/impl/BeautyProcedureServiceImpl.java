package io.github.rubensrabelo.project.mscommand.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil;

    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository,
            ModelMapper modelMapper,
            BrokerService brokerService) {
        this.beautyProcedureRepository = beautyProcedureRepository;
        this.brokerService = brokerService;

        this.converterUtil = new ConverterUtil<>(
                modelMapper,
                BeautyProceduresEntity.class,
                BeautyProcedureDTO.class);
    }

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO dto) {
        BeautyProceduresEntity entity = converterUtil.convertToSource(dto);
        entity = beautyProcedureRepository.save(entity);
        sendBeautyProceduresToQueue(entity);
        return converterUtil.convertToTarget(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> optional = beautyProcedureRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Beauty Procedure not found");
        }
        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO dto) {

        BeautyProceduresEntity entity = beautyProcedureRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        entity = beautyProcedureRepository.save(entity);

        sendBeautyProceduresToQueue(entity);

        return converterUtil.convertToTarget(entity);
    }

    private void sendBeautyProceduresToQueue(BeautyProceduresEntity entity) {
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
        brokerService.send("beautyProcedures", beautyProcedureDTO);
    }

}
