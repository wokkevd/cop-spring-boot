package com.kbc.foodie.service;

import com.kbc.foodie.client.TheMealDBClient;
import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBRecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBSearchResource;
import com.kbc.foodie.domain.RecipeEntity;
import com.kbc.foodie.factory.RecipeEntityFactory;
import com.kbc.foodie.factory.RecipeResourceFactory;
import com.kbc.foodie.repository.RecipeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final TheMealDBClient theMealDBClient;
    private final RecipeResourceFactory recipeResourceFactory;
    private final RecipeRepository recipeRepository;
    private final RecipeEntityFactory recipeEntityFactory;

    public RecipeService(TheMealDBClient theMealDBClient, RecipeResourceFactory recipeResourceFactory, RecipeRepository recipeRepository, RecipeEntityFactory recipeEntityFactory) {
        this.theMealDBClient = theMealDBClient;
        this.recipeResourceFactory = recipeResourceFactory;
        this.recipeRepository = recipeRepository;
        this.recipeEntityFactory = recipeEntityFactory;
    }

    public List<RecipeResource> search(String search) {
        ResponseEntity<MealDBSearchResource> mealDBResponse = theMealDBClient.search(search);
        if (mealDBResponse.getBody() == null) {
            return new ArrayList<>();
        }
        List<RecipeResource> recipeResources = recipeResourceFactory.create(mealDBResponse.getBody().getMeals());
        //Save all results to the database
        recipeRepository.saveAll(recipeEntityFactory.create(recipeResources));
        return recipeResources;
    }

    public Optional<RecipeResource> getRecipe(String recipeId) {
        Optional<RecipeEntity> storedRecipe = recipeRepository.findByRecipeId(recipeId);
        //If the recipe is found in the database, return that one
        if (storedRecipe.isPresent()) {
            return Optional.of(recipeResourceFactory.create(storedRecipe.get()));
        }

        ResponseEntity<MealDBSearchResource> mealDBResponse = theMealDBClient.getRecipe(recipeId);
        //If the recipe is not found on the TheMealDB, return an empty result
        if (mealDBResponse.getBody() == null) {
            return Optional.empty();
        }

        List<MealDBRecipeResource> mealDBRecipes = mealDBResponse.getBody().getMeals();
        //If multiple recipes exist with the same id, throw an illegal state exception
        if (mealDBRecipes.size() != 1) {
            throw new IllegalStateException("Found multiple recipes for id " + recipeId);
        }

        RecipeResource recipeResource = recipeResourceFactory.create(mealDBRecipes.get(0));
        //Save the retrieved recipe to the database
        recipeRepository.save(recipeEntityFactory.create(recipeResource));
        return Optional.of(recipeResource);
    }
}
