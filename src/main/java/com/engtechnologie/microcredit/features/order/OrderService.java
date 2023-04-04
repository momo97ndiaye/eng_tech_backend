package com.engtechnologie.microcredit.features.order;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.features.customer.CustomerService;
import com.engtechnologie.microcredit.features.workflow.WorkflowService;
import com.engtechnologie.microcredit.features.workflow.WorkflowStepEnum;
import com.engtechnologie.microcredit.reference.lender.*;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final LenderService lenderService;

    private final OrderMapper mapper;

    private final LenderMapper lenderMapper;

    private final CustomerService customerService;

    private final WorkflowService workflowService;

    OrderDto create(OrderDto orderDto) {

        var orderEntity = mapper.toEntity(orderDto);
        orderEntity.setCode(CodeGenerator.generateCode("ODR-"));
        CustomerEntity customerEntity = customerService.findById(orderDto.customerDto().id());
        orderEntity.setCustomer(customerEntity);
        orderEntity.setOrderDate(Instant.now());
        Set<LenderEntity> lenders = new HashSet<>();
        for (LenderDto lenderDto : orderDto.lendersDto()) {
            LenderEntity lenderEntity = lenderService.findById(lenderDto.id());
            lenders.add(lenderEntity);
        }
        orderEntity.setLenders(lenders);
        OrderEntity order = repository.save(orderEntity);

        workflowService.createWorkflow(order, WorkflowStepEnum.CREATED);

        return mapper.toDto(order);
    }

    Collection<OrderDto> getAll(Pageable pageable) {
        Page<OrderEntity> orderEntityPage = repository.findAll(pageable);
        return mapper.toDto(orderEntityPage.toList());
    }

    public OrderDto getById(Integer orderId) {
        return mapper.toDto(loadEntity(orderId));
    }

    OrderDto update(Integer id, OrderDto orderDto) {
        var orderEntity = loadEntity(id);

        mapper.updateEntity(orderDto, orderEntity);
        if(orderEntity.getCode() == null) {
            orderEntity.setCode(CodeGenerator.generateCode("ODR-"));
        }
        return mapper.toDto(repository.save(orderEntity));
    }

    OrderEntity deleteById(Integer orderId) {

        var demandeEntity = loadEntity(orderId);
        return repository.save(demandeEntity);
    }

    public OrderEntity changeStatus(Integer orderId, OrderStatusEnum status) {
    	var orderEntity = loadEntity(orderId);
        orderEntity.setStatus(status);

        switch (status) {
            case ACCEPTED -> workflowService.createWorkflow(orderEntity, WorkflowStepEnum.ACCEPTED);
            case REFUSED -> workflowService.createWorkflow(orderEntity, WorkflowStepEnum.REFUSED);
            case OFFERED -> workflowService.createWorkflow(orderEntity, WorkflowStepEnum.OFFER_SENT);
            default -> {
            }
        }


    	return repository.save(orderEntity);
    }

    private OrderEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<OrderDto> getDemandeFromStatus(OrderStatusEnum status) {
        Collection<OrderEntity> orderEntity = repository.findAllByStatus(status);
        return mapper.toDto(orderEntity);
    }
    
    public Collection<OrderDto> getDemandeFromCustomer(Integer id) {
        return mapper.toDto(repository.findAllByCustomerId(id));
    }
}
