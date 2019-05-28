package com.kbc.starwars.factory;

import com.kbc.starwars.contract.PersonResource;
import com.kbc.starwars.contract.swapi.SwapiPeopleResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonResourceFactory {

    public PersonResource create(SwapiPeopleResource swapiPeopleResource) {
        return PersonResource.builder()
                .name(swapiPeopleResource.getName())
                .height(swapiPeopleResource.getHeight())
                .mass(swapiPeopleResource.getMass())
                .hairColor(swapiPeopleResource.getHairColor())
                .skinColor(swapiPeopleResource.getSkinColor())
                .eyeColor(swapiPeopleResource.getEyeColor())
                .gender(swapiPeopleResource.getGender())
                .build();
    }

    public List<PersonResource> create(List<SwapiPeopleResource> swapiPeopleResources) {
        return swapiPeopleResources.stream().map(this::create).collect(Collectors.toList());
    }
}
