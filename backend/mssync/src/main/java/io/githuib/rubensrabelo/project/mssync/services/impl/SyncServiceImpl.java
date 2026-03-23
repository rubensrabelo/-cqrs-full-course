package io.githuib.rubensrabelo.project.mssync.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import io.githuib.rubensrabelo.project.mssync.dtos.appointments.FullAppointmentDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;
import io.githuib.rubensrabelo.project.mssync.services.AppointmentService;
import io.githuib.rubensrabelo.project.mssync.services.BeautyProcedureService;
import io.githuib.rubensrabelo.project.mssync.services.CustomerService;
import io.githuib.rubensrabelo.project.mssync.services.SyncService;
import io.githuib.rubensrabelo.project.mssync.utils.SyncLogger;

@Service
public class SyncServiceImpl implements SyncService {

    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final BeautyProcedureService beautyProcedureService;

    public SyncServiceImpl(AppointmentService appointmentService, CustomerService customerService, BeautyProcedureService beautyProcedureService){
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.beautyProcedureService = beautyProcedureService;
    }

    @Override
    public void syncCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            customerService.saveCustomer(customer);
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointment) {
        try{
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentService.saveAppointment(appointment);
        }catch(Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Saving beauty procedure: " + beautyProcedureDTO.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProcedureDTO);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProcedureDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
