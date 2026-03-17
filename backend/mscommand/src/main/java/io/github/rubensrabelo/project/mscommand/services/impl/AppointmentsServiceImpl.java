package io.github.rubensrabelo.project.mscommand.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.github.rubensrabelo.project.mscommand.dtos.AppointmentDTO;
import io.github.rubensrabelo.project.mscommand.entities.AppointmentsEntity;
import io.github.rubensrabelo.project.mscommand.repositories.AppointmentRepository;
import io.github.rubensrabelo.project.mscommand.repositories.BeautyProcedureRepository;
import io.github.rubensrabelo.project.mscommand.repositories.CustomerRepository;
import io.github.rubensrabelo.project.mscommand.services.AppointmentsService;
import io.github.rubensrabelo.project.mscommand.services.BrokerService;
import io.github.rubensrabelo.project.mscommand.utils.ConverterUtil;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final AppointmentRepository appointmentRepository;
    private final BeautyProcedureRepository beautyProcedureRepository;
    private final CustomerRepository customerRepository;
    private final BrokerService brokerService;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil =
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
    public AppointmentDTO create(final AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public AppointmentDTO update(final AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(final AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public void delete(final Long id) {
    }

}
