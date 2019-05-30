package com.kbc.starwars.client;

import com.kbc.starwars.config.SwapiConfiguration;
import com.kbc.starwars.contract.swapi.SwapiPeopleResource;
import com.kbc.starwars.contract.swapi.SwapiPeopleSearchResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi-client", url = "https://swapi.co/api/", configuration = SwapiConfiguration.class)
@Component
public interface SwapiClient {

    @GetMapping("/people/{peopleId}")
    ResponseEntity<SwapiPeopleResource> getPeople(@PathVariable Integer peopleId);

    @GetMapping("/people")
    ResponseEntity<SwapiPeopleSearchResource> findPeople(@RequestParam String search);
}
