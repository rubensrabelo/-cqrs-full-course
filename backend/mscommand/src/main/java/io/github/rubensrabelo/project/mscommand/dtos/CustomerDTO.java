package io.github.rubensrabelo.project.mscommand.dtos;

public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;

    private CustomerDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String phone;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public CustomerDTO build() {
            return new CustomerDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}