package io.github.rubensrabelo.project.mscommand.dtos;

import java.math.BigDecimal;

public record BeautyProcedureDTO(
    Long id,
    String name,
    String description,
    BigDecimal price
) {
    
}
