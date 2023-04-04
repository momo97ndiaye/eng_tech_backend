package com.engtechnologie.microcredit.features.workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class WorkflowController implements WorkflowApi {

    private final WorkflowService workflowService;


    @Override
    public Collection<WorkflowDto> getAll() {
        return null;
    }

    @Override
    public Collection<WorkflowDto> getAllByOrder(Integer id) {
        return workflowService.getAllByOrder(id);
    }
}
