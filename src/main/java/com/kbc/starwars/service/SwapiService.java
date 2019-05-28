package com.kbc.starwars.service;

import com.kbc.starwars.client.SwapiClient;
import com.kbc.starwars.contract.PersonResource;
import com.kbc.starwars.contract.swapi.SwapiPeopleResource;
import com.kbc.starwars.factory.PersonResourceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SwapiService {

    private final SwapiClient swapiClient;
    private final PersonResourceFactory personResourceFactory;

    public SwapiService(SwapiClient swapiClient, PersonResourceFactory personResourceFactory) {
        this.swapiClient = swapiClient;
        this.personResourceFactory = personResourceFactory;
    }

    public Optional<PersonResource> getPerson(Integer personId) {
        ResponseEntity<SwapiPeopleResource> peopleResponse = swapiClient.getPeople(personId);
        if (peopleResponse.hasBody() && peopleResponse.getBody() != null) {
            return Optional.of(personResourceFactory.create(peopleResponse.getBody()));
        }
        return Optional.empty();
    }

    public List<PersonResource> findPerson(String search) {
        ResponseEntity<List<SwapiPeopleResource>> searchResponse = swapiClient.findPeople(search);
        if (searchResponse.hasBody() && searchResponse.getBody() != null) {
            return personResourceFactory.create(searchResponse.getBody());
        }
        return new ArrayList<>();
    }
}
