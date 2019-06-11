package com.kbc.foodie.factory;

import com.kbc.foodie.contract.IngredientResource;
import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBRecipeResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeResourceFactory {

    public List<RecipeResource> create(List<MealDBRecipeResource> mealDBRecipeResources) {
        return mealDBRecipeResources.stream().map(this::create).collect(Collectors.toList());
    }

    public RecipeResource create(MealDBRecipeResource mealDBRecipeResource) {
        return RecipeResource.builder()
                .recipeId(mealDBRecipeResource.getIdMeal())
                .name(mealDBRecipeResource.getStrMeal())
                .category(mealDBRecipeResource.getStrCategory())
                .origin(mealDBRecipeResource.getStrArea())
                .picture(mealDBRecipeResource.getStrMealThumb())
                .video(mealDBRecipeResource.getStrYoutube())
                .instructions(mealDBRecipeResource.getStrInstructions())
                .ingredients(mapIngredients(mealDBRecipeResource))
                .build();
    }

    private List<IngredientResource> mapIngredients(MealDBRecipeResource mealDBRecipeResource) {
        List<IngredientResource> ingredients = new ArrayList<>();
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure1(), mealDBRecipeResource.getStrIngredient1()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure2(), mealDBRecipeResource.getStrIngredient2()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure3(), mealDBRecipeResource.getStrIngredient3()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure4(), mealDBRecipeResource.getStrIngredient4()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure5(), mealDBRecipeResource.getStrIngredient5()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure6(), mealDBRecipeResource.getStrIngredient6()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure7(), mealDBRecipeResource.getStrIngredient7()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure8(), mealDBRecipeResource.getStrIngredient8()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure9(), mealDBRecipeResource.getStrIngredient9()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure10(), mealDBRecipeResource.getStrIngredient10()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure11(), mealDBRecipeResource.getStrIngredient11()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure12(), mealDBRecipeResource.getStrIngredient12()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure13(), mealDBRecipeResource.getStrIngredient13()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure14(), mealDBRecipeResource.getStrIngredient14()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure15(), mealDBRecipeResource.getStrIngredient15()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure16(), mealDBRecipeResource.getStrIngredient16()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure17(), mealDBRecipeResource.getStrIngredient17()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure18(), mealDBRecipeResource.getStrIngredient18()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure19(), mealDBRecipeResource.getStrIngredient19()));
        ingredients.add(new IngredientResource(mealDBRecipeResource.getStrMeasure20(), mealDBRecipeResource.getStrIngredient20()));
        return ingredients.stream()
                .filter(i -> i.getQuantity() != null && !"".equals(i.getQuantity()))
                .filter(i -> i.getName() != null && !"".equals(i.getName()))
                .collect(Collectors.toList());
    }
}
