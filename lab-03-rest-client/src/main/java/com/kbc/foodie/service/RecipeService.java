package com.kbc.foodie.service;

import com.kbc.foodie.client.TheMealDBClient;
import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBRecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBSearchResource;
import com.kbc.foodie.factory.RecipeResourceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final TheMealDBClient theMealDBClient;
    private final RecipeResourceFactory recipeResourceFactory;

    public RecipeService(TheMealDBClient theMealDBClient, RecipeResourceFactory recipeResourceFactory) {
        this.theMealDBClient = theMealDBClient;
        this.recipeResourceFactory = recipeResourceFactory;
    }

    public List<RecipeResource> search(String search) {
        ResponseEntity<MealDBSearchResource> mealDBResponse = theMealDBClient.search(search);
        if (mealDBResponse.getBody() == null) {
            return new ArrayList<>();
        }
        return recipeResourceFactory.create(mealDBResponse.getBody().getMeals());
    }

    public Optional<RecipeResource> getRecipe(String recipeId) {
        ResponseEntity<MealDBSearchResource> mealDBResponse = theMealDBClient.getRecipe(recipeId);
        if (mealDBResponse.getBody() == null) {
            return Optional.empty();
        }
        List<MealDBRecipeResource> mealDBRecipes = mealDBResponse.getBody().getMeals();
        if (mealDBRecipes.size() != 1) {
            throw new IllegalStateException("Found multiple recipes for id " + recipeId);
        }
        return Optional.of(recipeResourceFactory.create(mealDBRecipes.get(0)));
    }
}
