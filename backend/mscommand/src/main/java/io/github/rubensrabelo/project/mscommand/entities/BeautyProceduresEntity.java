package io.github.rubensrabelo.project.mscommand.entities;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "beauty_procedures")
public class BeautyProceduresEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "beautyProcedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentsEntity> appointments;

    public BeautyProceduresEntity() {
    }

    private BeautyProceduresEntity(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.appointments = builder.appointments;
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

    public List<AppointmentsEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsEntity> appointments) {
        this.appointments = appointments;
    }

    public static class Builder {
        private String name;
        private String description;
        private BigDecimal price;
        private List<AppointmentsEntity> appointments;

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

        public Builder appointments(List<AppointmentsEntity> appointments) {
            this.appointments = appointments;
            return this;
        }

        public BeautyProceduresEntity build() {
            return new BeautyProceduresEntity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}