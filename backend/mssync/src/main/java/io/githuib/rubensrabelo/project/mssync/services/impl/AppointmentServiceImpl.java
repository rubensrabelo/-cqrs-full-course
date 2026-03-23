package io.githuib.rubensrabelo.project.mssync.services.impl;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import io.githuib.rubensrabelo.project.mssync.dtos.appointments.FullAppointmentDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;
import io.githuib.rubensrabelo.project.mssync.repositories.AppointmentRepository;
import io.githuib.rubensrabelo.project.mssync.services.AppointmentService;
import io.githuib.rubensrabelo.project.mssync.utils.SyncLogger;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final MongoTemplate mongoTemplate;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, MongoTemplate mongoTemplate) {
        this.appointmentRepository = appointmentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveAppointment(FullAppointmentDTO appointment) {
        try{
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentRepository.save(appointment);
        }catch (Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating appointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try{
        SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
        Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDTO.getId()));
        Update update = new Update()
                .set("beautyProcedure.name", beautyProcedureDTO.getName())
                .set("beautyProcedure.description", beautyProcedureDTO.getDescription());
        mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating appointment beauty procedure: "+ e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
