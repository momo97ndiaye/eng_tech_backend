package com.engtechnologie.microcredit.features.workflow;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    private final WorkflowMapper workflowMapper;

    public Collection<WorkflowDto> getAllByOrder(Integer id) {
        return workflowRepository.findAllByOrderId(id).stream()
                .map(workflowMapper::toDto)
                .toList();
    }

    public void create(WorkflowDto workflowDto) {
        workflowRepository.save(workflowMapper.toEntity(workflowDto));
    }

    public void createWorkflow(OrderEntity order, WorkflowStepEnum workflowStatus) {
        WorkflowEntity workflowEntity = new WorkflowEntity();
        workflowEntity.setOrder(order);
        workflowEntity.setStep(workflowStatus);
        workflowEntity.setLabel(workflowStatus.getLabel());
        workflowEntity.setStepDate(LocalDate.now());
        workflowRepository.save(workflowEntity);
    }
}
