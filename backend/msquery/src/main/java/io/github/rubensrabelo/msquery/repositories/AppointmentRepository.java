package io.github.rubensrabelo.msquery.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.rubensrabelo.msquery.dtos.appointments.FullAppointmentDTO;

@Repository
public interface AppointmentRepository extends MongoRepository<FullAppointmentDTO, Long> {   
    @Query("{ 'customerId' : ?0}")
    List<FullAppointmentDTO> listAppointmentsByCustomerId(Long customerId);
    @Query("{ 'beautyProcedureId' : ?0}")
    List<FullAppointmentDTO> listAppointmentsByBeautyProcedureId(Long beautyProcedureId);
}
