package io.github.rubensrabelo.project.mscommand.dtos;

import java.time.LocalDateTime;

public record AppointmentDTO(
    Long id,
    LocalDateTime dateTime,
    Boolean appointmentsOpen,

    Long customer,
    Long beautyProcedure
) {
}
