package com.engtechnologie.microcredit.controller;

import com.engtechnologie.microcredit.model.ConsommationFrom;
import com.engtechnologie.microcredit.repository.ConsommationFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cons-form")
@RequiredArgsConstructor
public class ConsommationController {


    private final ConsommationFormRepository consommationFormRepository;

    @GetMapping
    public List<ConsommationFrom> getAllDemands() {
        return consommationFormRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity< ConsommationFrom > getDemandById(@PathVariable(value = "id") Long id)
            throws Exception {
        ConsommationFrom demand = consommationFormRepository.findById(id)
                .orElseThrow(() -> new Exception("Pas de demande avec l'id suivant :: " + id));
        return ResponseEntity.ok().body(demand);
    }

    @PostMapping
    public ConsommationFrom createDemand(@RequestBody ConsommationFrom demand) {
        return consommationFormRepository.save(demand);
    }
}
