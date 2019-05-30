package com.kbc.starwars.contract.swapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwapiPeopleSearchResource {

    private Integer count;
    private String next;
    private String previous;
    private List<SwapiPeopleResource> results;
}
