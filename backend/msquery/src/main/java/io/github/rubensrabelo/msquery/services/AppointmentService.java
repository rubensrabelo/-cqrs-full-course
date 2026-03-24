package io.github.rubensrabelo.msquery.services;

import java.util.List;

import io.github.rubensrabelo.msquery.dtos.appointments.FullAppointmentDTO;

public interface AppointmentService {
    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> ListAllAppointmentsByCustomer(Long customerId);
    List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedure(Long beautyProcedureId);
}
