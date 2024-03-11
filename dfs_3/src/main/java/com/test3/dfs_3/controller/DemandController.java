package com.test3.dfs_3.controller;

import com.test3.dfs_3.entity.Demand;
import com.test3.dfs_3.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demands")
public class DemandController {
    private final DemandRepository demandRepository;

    @Autowired
    public DemandController(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    @PostMapping
    public ResponseEntity<Demand> createDemand(@RequestBody Demand demand) {
        Demand savedDemand = demandRepository.save(demand);
        return new ResponseEntity<>(savedDemand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable Long id) {
        return demandRepository.findById(id)
                .map(demand -> new ResponseEntity<>(demand, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
        demandRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
