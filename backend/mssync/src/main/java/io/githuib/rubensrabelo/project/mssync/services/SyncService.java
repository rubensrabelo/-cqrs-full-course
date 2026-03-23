package io.githuib.rubensrabelo.project.mssync.services;

import io.githuib.rubensrabelo.project.mssync.dtos.appointments.FullAppointmentDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;

public interface SyncService {
     void syncCustomer(CustomerDTO customer);
    void syncAppointment(FullAppointmentDTO appointment);
    void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO);
}
