package com.engtechnologie.microcredit.features.customer;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;


    CustomerDto create(CustomerDto customerDto) {

        var customerEntity = mapper.toEntity(customerDto);
        customerEntity.setCode(CodeGenerator.generateCode("CST-"));
        if (customerEntity.getCreatedFrom() == null) {
            customerEntity.setCreatedFrom(CustomerTypeEnum.WEB);
        }

        if(customerEntity.getCreatedFrom() == CustomerTypeEnum.MOBILE) {
            // create a customer account

        }
        return mapper.toDto(repository.save(customerEntity));
    }

    Collection<CustomerDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    CustomerDto getById(Integer customerId) {
        return mapper.toDto(loadEntity(customerId));
    }

    CustomerDto update(Integer customerId, CustomerDto customerDto) {

        var customerEntity = loadEntity(customerId);
        mapper.updateEntity(customerDto, customerEntity);
        customerEntity.setCode(CodeGenerator.generateCode("CST-"));
        return mapper.toDto(repository.save(customerEntity));
    }

    CustomerEntity deleteById(Integer customerId) {

        var DemandeEntity = loadEntity(customerId);
        return repository.save(DemandeEntity);
    }

    CustomerEntity changeStatus(Integer customerId, CustomerStatusEnum status) {
    	var customerEntity = loadEntity(customerId);
        customerEntity.setStatus(status);
    	return repository.save(customerEntity);
    }

    private CustomerEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<CustomerDto> getcustomerFromStatus(CustomerStatusEnum status) {
        return mapper.toDto(repository.findAllByStatus(status));
    }

    public CustomerEntity findById(Integer customerId) {
        return repository.findById(customerId).orElseThrow(ResourceNotFoundException::new);
    }

    public CustomerDto getCustomerFromLoan(Integer id) {
        return mapper.toDto(repository.getCustomerFromLoan(id));
    }
}
