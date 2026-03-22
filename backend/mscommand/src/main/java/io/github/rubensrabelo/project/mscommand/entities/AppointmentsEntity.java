package io.github.rubensrabelo.project.mscommand.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments", schema = "beautique_schema")
public class AppointmentsEntity extends BaseEntity {
    
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Boolean appointmentsOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_procedure_id", nullable = true)
    private BeautyProceduresEntity beautyProcedure;

    public AppointmentsEntity() {
    }

    private AppointmentsEntity(Builder builder) {
        this.dateTime = builder.datetime;
        this.appointmentsOpen = builder.appointmentsOpen;
        this.customer = builder.customer;
        this.beautyProcedure = builder.beautyProcedure;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getAppointmentsOpen() {
        return appointmentsOpen;
    }

    public void setAppointmentsOpen(Boolean appointmentsOpen) {
        this.appointmentsOpen = appointmentsOpen;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public BeautyProceduresEntity getBeautyProcedure() {
        return beautyProcedure;
    }

    public void setBeautyProcedure(BeautyProceduresEntity beautyProcedure) {
        this.beautyProcedure = beautyProcedure;
    }

    public static class Builder {
        private LocalDateTime datetime;
        private Boolean appointmentsOpen;
        private CustomerEntity customer;
        private BeautyProceduresEntity beautyProcedure;

        public Builder datetime(LocalDateTime datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder appointmentsOpen(Boolean appointmentsOpen) {
            this.appointmentsOpen = appointmentsOpen;
            return this;
        }

        public Builder customer(CustomerEntity customer) {
            this.customer = customer;
            return this;
        }

        public Builder beautyProcedure(BeautyProceduresEntity beautyProcedure) {
            this.beautyProcedure = beautyProcedure;
            return this;
        }

        public AppointmentsEntity build() {
            return new AppointmentsEntity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
