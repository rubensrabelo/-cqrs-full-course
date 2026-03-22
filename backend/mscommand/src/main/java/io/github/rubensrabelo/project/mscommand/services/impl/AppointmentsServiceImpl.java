package io.github.rubensrabelo.project.mscommand.services.impl;

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

    private final ModelMapper modelMapper;

    private final AppointmentRepository appointmentRepository;
    private final BeautyProcedureRepository beautyProcedureRepository;
    private final CustomerRepository customerRepository;
    private final BrokerService brokerService;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil;

    public AppointmentsServiceImpl(
            ModelMapper modelMapper,
            AppointmentRepository appointmentRepository,
            BeautyProcedureRepository beautyProcedureRepository,
            CustomerRepository customerRepository,
            BrokerService brokerService) {
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
        this.beautyProcedureRepository = beautyProcedureRepository;
        this.customerRepository = customerRepository;
        this.brokerService = brokerService;

        this.converterUtil = new ConverterUtil<>(
                modelMapper,
                AppointmentsEntity.class,
                AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO dto) {

        AppointmentsEntity entity = converterUtil.convertToSource(dto);

        entity.setAppointmentsOpen(true);

        entity = appointmentRepository.save(entity);

        sendAppointmentToQueue(entity);

        return converterUtil.convertToTarget(entity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO dto) {

        AppointmentsEntity entity = appointmentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        entity.setDateTime(dto.getDateTime());
        entity.setAppointmentsOpen(dto.getAppointmentsOpen());

        entity = appointmentRepository.save(entity);

        sendAppointmentToQueue(entity);

        return converterUtil.convertToTarget(entity);
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
        CustomerDTO customerDTO = entity.getCustomer() != null
                ? modelMapper.map(entity.getCustomer(), CustomerDTO.class)
                : null;

        BeautyProcedureDTO beautyProcedureDTO = entity.getBeautyProcedure() != null
                ? modelMapper.map(entity.getBeautyProcedure(), BeautyProcedureDTO.class)
                : null;

        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .appointmentsOpen(entity.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found"));
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity entity) {
        return AppointmentDTO.builder()
                .id(entity.getId())
                .beautyProcedure(
                        entity.getBeautyProcedure() != null
                                ? entity.getBeautyProcedure().getId()
                                : null)
                .dateTime(entity.getDateTime())
                .appointmentsOpen(entity.getAppointmentsOpen())
                .customer(
                        entity.getCustomer() != null
                                ? entity.getCustomer().getId()
                                : null)
                .build();
    }
}
