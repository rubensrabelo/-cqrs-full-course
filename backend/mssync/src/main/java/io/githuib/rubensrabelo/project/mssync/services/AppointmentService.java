package io.githuib.rubensrabelo.project.mssync.services;

import io.githuib.rubensrabelo.project.mssync.dtos.appointments.FullAppointmentDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;

public interface AppointmentService {
    void saveAppointment(FullAppointmentDTO appointment);
    void updateAppointmentCustomer(CustomerDTO customer);
    void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO);
}
