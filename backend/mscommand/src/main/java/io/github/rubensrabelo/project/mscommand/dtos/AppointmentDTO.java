package io.github.rubensrabelo.project.mscommand.dtos;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;
    private Long customer;
    private Long beautyProcedure;

    public AppointmentDTO() {
    }

    private AppointmentDTO(Builder builder) {
        this.id = builder.id;
        this.dateTime = builder.dateTime;
        this.appointmentsOpen = builder.appointmentsOpen;
        this.customer = builder.customer;
        this.beautyProcedure = builder.beautyProcedure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getBeautyProcedure() {
        return beautyProcedure;
    }

    public void setBeautyProcedure(Long beautyProcedure) {
        this.beautyProcedure = beautyProcedure;
    }

    public static class Builder {
        private Long id;
        private LocalDateTime dateTime;
        private Boolean appointmentsOpen;
        private Long customer;
        private Long beautyProcedure;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder appointmentsOpen(Boolean appointmentsOpen) {
            this.appointmentsOpen = appointmentsOpen;
            return this;
        }

        public Builder customer(Long customer) {
            this.customer = customer;
            return this;
        }

        public Builder beautyProcedure(Long beautyProcedure) {
            this.beautyProcedure = beautyProcedure;
            return this;
        }

        public AppointmentDTO build() {
            return new AppointmentDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}