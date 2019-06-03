package com.kbc.foodie.service;

import com.kbc.foodie.contract.IngredientResource;
import com.kbc.foodie.contract.RecipeResource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private static final String RECIPE_ID = "98765";
    private static final String RECIPE_NAME = "Hot Ma Po Tofu";
    private static final String RECIPE_CATEGORY = "Spicy";
    private static final String RECIPE_ORIGIN = "Chinese";
    private static final String RECIPE_PICTURE_URL = "https://thewoksoflife.com/wp-content/uploads/2014/03/Mapo-tofu-65.jpg";
    private static final String RECIPE_INSTRUCTIONS = "Put ingredients in hot pot. Do stuff. Serve hot. Provide rice for those who can't handle hot food.";
    private static final String RECIPE_INGREDIENT_1_QUANTITY = "A bunch";
    private static final String RECIPE_INGREDIENT_1_NAME = "Tofu";
    private static final String RECIPE_INGREDIENT_2_QUANTITY = "A bag";
    private static final String RECIPE_INGREDIENT_2_NAME = "Hot pot spices";
    private static final String RECIPE_INGREDIENT_3_QUANTITY = "Loads";
    private static final String RECIPE_INGREDIENT_3_NAME = "Everything else";

    public List<RecipeResource> search(String search) {
        return Collections.singletonList(getDummyRecipe(RECIPE_ID));

    }

    public Optional<RecipeResource> getRecipe(String recipeId) {
        return Optional.of(getDummyRecipe(recipeId));
    }

    private RecipeResource getDummyRecipe(String recipeId) {
        return RecipeResource.builder()
                .recipeId(recipeId)
                .name(RECIPE_NAME)
                .category(RECIPE_CATEGORY)
                .origin(RECIPE_ORIGIN)
                .picture(RECIPE_PICTURE_URL)
                .instructions(RECIPE_INSTRUCTIONS)
                .ingredients(Arrays.asList(
                        new IngredientResource(RECIPE_INGREDIENT_1_QUANTITY, RECIPE_INGREDIENT_1_NAME),
                        new IngredientResource(RECIPE_INGREDIENT_2_QUANTITY, RECIPE_INGREDIENT_2_NAME),
                        new IngredientResource(RECIPE_INGREDIENT_3_QUANTITY, RECIPE_INGREDIENT_3_NAME)
                ))
                .build();
    }
}
