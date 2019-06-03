package com.kbc.foodie.factory;

import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.domain.IngredientEntity;
import com.kbc.foodie.domain.RecipeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeEntityFactory {

    public List<RecipeEntity> create(List<RecipeResource> recipeResources) {
        return recipeResources.stream().map(this::create).collect(Collectors.toList());
    }

    public RecipeEntity create(RecipeResource recipeResource) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeId(recipeResource.getRecipeId());
        recipeEntity.setName(recipeResource.getName());
        recipeEntity.setCategory(recipeResource.getCategory());
        recipeEntity.setOrigin(recipeResource.getOrigin());
        recipeEntity.setInstructions(recipeResource.getInstructions());
        recipeEntity.setPicture(recipeResource.getPicture());
        recipeEntity.setVideo(recipeResource.getVideo());
        recipeEntity.setIngredientEntities(mapIngredients(recipeResource, recipeEntity));
        return recipeEntity;
    }

    private List<IngredientEntity> mapIngredients(RecipeResource recipeResource, RecipeEntity recipeEntity) {
        return recipeResource.getIngredients().stream()
                .map(i -> new IngredientEntity(i.getQuantity(), i.getName(), recipeEntity))
                .collect(Collectors.toList());
    }
}
