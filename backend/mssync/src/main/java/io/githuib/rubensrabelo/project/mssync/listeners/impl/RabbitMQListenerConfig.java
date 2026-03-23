package io.githuib.rubensrabelo.project.mssync.listeners.impl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import io.githuib.rubensrabelo.project.mssync.dtos.appointments.FullAppointmentDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.beautyprocedures.BeautyProcedureDTO;
import io.githuib.rubensrabelo.project.mssync.dtos.customers.CustomerDTO;
import io.githuib.rubensrabelo.project.mssync.listeners.ListenerConfig;
import io.githuib.rubensrabelo.project.mssync.services.SyncService;
import io.githuib.rubensrabelo.project.mssync.utils.SyncLogger;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableRabbit
public class RabbitMQListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;
    private final SyncService syncService;

    public RabbitMQListenerConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService  = syncService;
    }

    @RabbitListener(queues="customerQueue")
    @Override
    public void listenToCustomerQueue(String message) {
        try{
            CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customer);
            SyncLogger.info("Message received from queue customerQueue: " + customer.toString());
        }catch (Exception e){
            SyncLogger.error("Error listen customer queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "appointmentQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            FullAppointmentDTO appointments = objectMapper.readValue(message, FullAppointmentDTO.class);
            syncService.syncAppointment(appointments);
            SyncLogger.info("Message received from queue appointmentQueue: " + appointments.toString());
        }catch (Exception e){
            SyncLogger.error("Error listen appointment queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProcedureQueue")
    @Override
    public void listenToBeautyProcedureQueue(String message) {
        try{
            BeautyProcedureDTO beautyProcedures = objectMapper.readValue(message, BeautyProcedureDTO.class);
            syncService.syncBeautyProcedures(beautyProcedures);
            SyncLogger.info("Message received from queue beautyProcedureQueue: " + beautyProcedures.toString());
        }catch(Exception e){
            SyncLogger.error("Error listen beauty procedure queue: " + e.getMessage());
        }
    }
}
