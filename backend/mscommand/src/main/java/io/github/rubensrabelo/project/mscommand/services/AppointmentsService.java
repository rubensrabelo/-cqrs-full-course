package io.github.rubensrabelo.project.mscommand.services;

import io.github.rubensrabelo.project.mscommand.dtos.AppointmentDTO;

public interface AppointmentsService {

    AppointmentDTO create(AppointmentDTO dto);
    AppointmentDTO update(AppointmentDTO dto);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO dto);
    void delete(Long id);
}
