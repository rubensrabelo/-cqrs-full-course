package io.githuib.rubensrabelo.project.mssync.listeners;

public interface ListenerConfig {
    void listenToCustomerQueue(String message);
    void listenToAppointmentQueue(String message);
    void listenToBeautyProcedureQueue(String message);
}
