package com.kbc.starwars.factory;

import com.kbc.starwars.contract.IngredientResource;
import com.kbc.starwars.contract.RecipeResource;
import com.kbc.starwars.contract.mealdb.MealDBRecipeResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RecipeResourceFactory {

    public RecipeResource create(MealDBRecipeResource mealDBRecipeResource) {

        new RecipeResource()
    }

    private List<IngredientResource> mapIngredients(MealDBRecipeResource mealDBRecipeResource) {
        List<IngredientResource> ingredients = new ArrayList<>();
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure1(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure2(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure3(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure4(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure5(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure6(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure7(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure8(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure9(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure10(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure11(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure12(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure13(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure14(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure15(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure16(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure17(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure18(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure19(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure20(), mealDBRecipeResource.getStrIngredient1()));
        return ingredients;
    }
}
