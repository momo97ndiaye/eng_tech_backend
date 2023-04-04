package com.engtechnologie.microcredit.features.workflow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/api/workflows")
public interface WorkflowApi {

    @GetMapping
    Collection<WorkflowDto> getAll();

    @GetMapping("/order/{id}")
    Collection<WorkflowDto> getAllByOrder(@PathVariable Integer id);
}
