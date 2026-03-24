package io.github.rubensrabelo.msquery.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.rubensrabelo.msquery.dtos.appointments.FullAppointmentDTO;
import io.github.rubensrabelo.msquery.repositories.AppointmentRepository;
import io.github.rubensrabelo.msquery.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointments() {
        try{
            return appointmentRepository.findAll();
        }catch (Exception e){
          throw new RuntimeException("Error listing all appointments");
        }
    }

    @Override
    public List<FullAppointmentDTO> ListAllAppointmentsByCustomer(Long customerId) {
        try{
            return appointmentRepository.listAppointmentsByCustomerId(customerId);
        }catch (Exception e){
            throw new RuntimeException("Error listint all appointments by customer");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedure(Long beautyProcedureId) {
        try{
            return appointmentRepository.listAppointmentsByBeautyProcedureId(beautyProcedureId);
        }catch (Exception e){
            throw new RuntimeException("Error listint all appointments by beauty procedure");
        }
    }
}
