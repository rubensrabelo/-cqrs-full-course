package io.github.rubensrabelo.project.mscommand.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.github.rubensrabelo.project.mscommand.dtos.AppointmentDTO;
import io.github.rubensrabelo.project.mscommand.dtos.BeautyProcedureDTO;
import io.github.rubensrabelo.project.mscommand.dtos.CustomerDTO;
import io.github.rubensrabelo.project.mscommand.dtos.FullAppointmentDTO;
import io.github.rubensrabelo.project.mscommand.entities.AppointmentsEntity;
import io.github.rubensrabelo.project.mscommand.entities.BeautyProceduresEntity;
import io.github.rubensrabelo.project.mscommand.entities.CustomerEntity;
import io.github.rubensrabelo.project.mscommand.repositories.AppointmentRepository;
import io.github.rubensrabelo.project.mscommand.repositories.BeautyProcedureRepository;
import io.github.rubensrabelo.project.mscommand.repositories.CustomerRepository;
import io.github.rubensrabelo.project.mscommand.services.AppointmentsService;
import io.github.rubensrabelo.project.mscommand.services.BrokerService;
import io.github.rubensrabelo.project.mscommand.utils.ConverterUtil;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final ModelMapper modelMapper = new ModelMapper();

    private AppointmentRepository appointmentRepository;
    private BeautyProcedureRepository beautyProcedureRepository;
    private CustomerRepository customerRepository;
    private BrokerService brokerService;

    private ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil =
    new ConverterUtil<>(
        AppointmentsEntity.class,
        AppointmentDTO.class
    );

    public AppointmentsServiceImpl(AppointmentRepository appointmentRepository,
            BeautyProcedureRepository beautyProcedureRepository, CustomerRepository customerRepository,
            BrokerService brokerService) {
        this.appointmentRepository = appointmentRepository;
        this.beautyProcedureRepository = beautyProcedureRepository;
        this.customerRepository = customerRepository;
        this.brokerService = brokerService;
    }

    @Override
    public AppointmentDTO create(AppointmentDTO dto) {
        AppointmentsEntity entity = converterUtil.convertToSource(dto);
        entity = appointmentRepository.save(entity);
        sendAppointmentToQueue(entity);
        return converterUtil.convertToTarget(entity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO dto) {
        Optional<AppointmentsEntity> optional = appointmentRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new RuntimeException("Appointment not found");
        }
        AppointmentsEntity dtoToEntity = converterUtil.convertToSource(dto);
        AppointmentsEntity updatedEntity = appointmentRepository.save(dtoToEntity);
        sendAppointmentToQueue(updatedEntity);
        return converterUtil.convertToTarget(updatedEntity);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO dto) {
        CustomerEntity customerEntity = findCustomerById(dto.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(dto.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(dto.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsOpen(false);
        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(updatedAppointmentEntity);
        return buildAppointmentsDTO(updatedAppointmentEntity);
    }

    @Override
    public void delete(Long id) {
        AppointmentsEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(entity);
    }

    private void sendAppointmentToQueue(AppointmentsEntity entity) {
        CustomerDTO customerDTO = entity.getCustomer() != null ?
                    modelMapper.map(entity.getCustomer(), CustomerDTO.class) : null;
        
        BeautyProcedureDTO beautyProcedureDTO = entity.getBeautyProcedure() != null ? modelMapper.map(entity.getBeautyProcedure(), BeautyProcedureDTO.class) : null;
        
        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .appointmentsOpen(entity.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
    }

    private CustomerEntity findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id){
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found"));
    }

    private AppointmentsEntity findAppointmentById(Long id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity entity){
        return AppointmentDTO.builder()
                .id(entity.getId())
                .beautyProcedure(entity.getBeautyProcedure().getId())
                .dateTime(entity.getDateTime())
                .appointmentsOpen(entity.getAppointmentsOpen())
                .customer(entity.getCustomer().getId())
                .build();
    }
}
