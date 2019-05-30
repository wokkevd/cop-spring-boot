package com.kbc.starwars.service;

import com.kbc.starwars.client.TheMealDBClient;
import com.kbc.starwars.contract.RecipeResource;
import com.kbc.starwars.contract.mealdb.MealDBSearchResource;
import com.kbc.starwars.factory.RecipeResourceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheMealDBService {

    private final TheMealDBClient theMealDBClient;
    private final RecipeResourceFactory recipeResourceFactory;

    public TheMealDBService(TheMealDBClient theMealDBClient) {
        this.theMealDBClient = theMealDBClient;
    }

    public List<RecipeResource> search(String search) {
        ResponseEntity<MealDBSearchResource> mealDBResponse = theMealDBClient.search(search);
        if (mealDBResponse.getBody() == null) {
            return new ArrayList<>();
        }
        return recipeResourceFactory.create(mealDBResponse.getBody().getMeals())
    }
}
