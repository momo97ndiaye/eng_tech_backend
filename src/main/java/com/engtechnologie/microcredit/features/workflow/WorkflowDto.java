package com.engtechnologie.microcredit.features.workflow;

import com.engtechnologie.microcredit.features.order.OrderDto;

public record WorkflowDto(Integer id, WorkflowStepEnum step, String label, OrderDto orderDto, String stepDate) {
}
