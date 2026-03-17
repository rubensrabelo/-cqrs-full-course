package io.github.rubensrabelo.project.mscommand.services;

import io.github.rubensrabelo.project.mscommand.dtos.AppointmentDTO;

public interface AppointmentsService {

    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO);
    void delete(Long id);
}
