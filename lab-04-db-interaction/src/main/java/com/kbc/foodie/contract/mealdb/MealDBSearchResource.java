package com.kbc.foodie.contract.mealdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDBSearchResource {

    private List<MealDBRecipeResource> meals;
}
