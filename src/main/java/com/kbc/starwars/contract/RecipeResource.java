package com.kbc.starwars.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeResource {

    private String recipeId;
    private String name;
    private String category;
    private String origin;
    private String instructions;
    private String picture;
    private String video;
    private List<IngredientResource> ingredients;
}
