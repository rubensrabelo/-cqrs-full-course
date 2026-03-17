package io.github.rubensrabelo.project.mscommand.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class AppointmentsEntity extends BaseEntity {
    
    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private LocalDateTime appointmentsOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customer;

    public AppointmentsEntity() {
    }

    private AppointmentsEntity(Builder builder) {
        this.datetime = builder.datetime;
        this.appointmentsOpen = builder.appointmentsOpen;
        this.customer = builder.customer;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public LocalDateTime getAppointmentsOpen() {
        return appointmentsOpen;
    }

    public void setAppointmentsOpen(LocalDateTime appointmentsOpen) {
        this.appointmentsOpen = appointmentsOpen;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public static class Builder {
        private LocalDateTime datetime;
        private LocalDateTime appointmentsOpen;
        private CustomerEntity customer;

        public Builder datetime(LocalDateTime datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder appointmentsOpen(LocalDateTime appointmentsOpen) {
            this.appointmentsOpen = appointmentsOpen;
            return this;
        }

        public Builder customer(CustomerEntity customer) {
            this.customer = customer;
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
