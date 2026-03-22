package io.github.rubensrabelo.project.mscommand.dtos;

import java.math.BigDecimal;

public class BeautyProcedureDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public BeautyProcedureDTO() {}

    private BeautyProcedureDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BeautyProcedureDTO build() {
            return new BeautyProcedureDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}