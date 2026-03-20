package io.github.rubensrabelo.project.mscommand.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.rubensrabelo.project.mscommand.dtos.CustomerDTO;
import io.github.rubensrabelo.project.mscommand.entities.CustomerEntity;
import io.github.rubensrabelo.project.mscommand.repositories.CustomerRepository;
import io.github.rubensrabelo.project.mscommand.services.BrokerService;
import io.github.rubensrabelo.project.mscommand.services.CustomerService;
import io.github.rubensrabelo.project.mscommand.utils.ConverterUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BrokerService brokerService;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    public CustomerServiceImpl(CustomerRepository customerRepository, BrokerService brokerService) {
        this.customerRepository = customerRepository;
        this.brokerService = brokerService;
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        CustomerEntity entity = converterUtil.convertToSource(dto);
        entity = customerRepository.save(entity);
        sendCustomerToQueue(entity);
        return converterUtil.convertToTarget(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> optional = customerRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.delete(optional.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
        Optional<CustomerEntity> optional = customerRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        CustomerEntity customerEntity = converterUtil.convertToSource(dto);

        customerEntity.setAppointments(optional.get().getAppointments());
        customerEntity.setCreatedAt(optional.get().getCreatedAt());
        CustomerDTO updatedCustomerDTO = converterUtil.convertToTarget(customerRepository.save(customerEntity));
        sendCustomerToQueue(customerEntity);
        return updatedCustomerDTO;
    }

    private void sendCustomerToQueue(CustomerEntity entity){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
        brokerService.send("customer", customerDTO);
    }
}
