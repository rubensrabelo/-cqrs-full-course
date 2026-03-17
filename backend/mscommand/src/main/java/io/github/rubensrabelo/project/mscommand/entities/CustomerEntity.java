package io.github.rubensrabelo.project.mscommand.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentsEntity> appointments;

    public CustomerEntity() {
    }

    public CustomerEntity(Builder builder) {
        this.name =  builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.appointments = builder.appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static class Builder {

        private String name;
        private String email;
        private String phone;
        private List<AppointmentsEntity> appointments;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder appointments(List<AppointmentsEntity> appointments) {
           this.appointments = appointments;
           return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
