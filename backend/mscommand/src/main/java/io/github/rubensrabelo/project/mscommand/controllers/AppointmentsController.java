package io.github.rubensrabelo.project.mscommand.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rubensrabelo.project.mscommand.dtos.AppointmentDTO;
import io.github.rubensrabelo.project.mscommand.services.AppointmentsService;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    public AppointmentsController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @PostMapping
    ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentsService.create(appointmentDTO));
    }

    @PatchMapping
    ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentsService.update(appointmentDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        appointmentsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<AppointmentDTO> setCustomerToAppointment(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentsService.setCustomerToAppointment(appointmentDTO));
    }
}
