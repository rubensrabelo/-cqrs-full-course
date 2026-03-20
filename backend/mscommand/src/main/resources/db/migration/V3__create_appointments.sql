CREATE TABLE IF NOT EXISTS appointments (
    id SERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    appointments_open BOOLEAN NOT NULL,

    customer_id INTEGER,
    beauty_procedure_id INTEGER,

    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_appointments_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_appointments_beauty_procedure
        FOREIGN KEY (beauty_procedure_id)
        REFERENCES beauty_procedures(id)
        ON DELETE SET NULL
);