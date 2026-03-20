package io.github.rubensrabelo.project.mscommand.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rubensrabelo.project.mscommand.dtos.BeautyProcedureDTO;
import io.github.rubensrabelo.project.mscommand.services.BeautyProcedureService;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    private final BeautyProcedureService beautyProcedureService;

    public BeautyProcedureController(BeautyProcedureService beautyProcedureService) {
        this.beautyProcedureService = beautyProcedureService;
    }

    @PostMapping
    ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO){
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        beautyProcedureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    ResponseEntity<BeautyProcedureDTO> update(@RequestBody BeautyProcedureDTO beautyProcedureDTO){
        return ResponseEntity.ok(beautyProcedureService.update(beautyProcedureDTO));
    }
}