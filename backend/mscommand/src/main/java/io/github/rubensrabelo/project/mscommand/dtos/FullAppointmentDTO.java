package io.github.rubensrabelo.project.mscommand.dtos;

import java.time.LocalDateTime;

public record FullAppointmentDTO(
    Long id,
    LocalDateTime dateTime,
    Boolean appointmentsOpen,
    CustomerDTO customer,
    BeautyProcedureDTO beautyProcedure
) {
}
