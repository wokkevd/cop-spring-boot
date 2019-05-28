package com.kbc.starwars.controller;

import com.kbc.starwars.contract.PersonResource;
import com.kbc.starwars.service.SwapiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final SwapiService swapiService;

    public PersonController(SwapiService swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResource> getPerson(@PathVariable Integer personId) {
        return swapiService.getPerson(personId)
                .map(personResource -> ResponseEntity.ok().body(personResource))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PersonResource> findPerson(@RequestParam String search) {
        return swapiService.findPerson(search);
    }
}
