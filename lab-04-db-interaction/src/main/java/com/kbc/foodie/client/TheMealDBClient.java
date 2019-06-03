package com.kbc.foodie.client;

import com.kbc.foodie.contract.mealdb.MealDBRecipeResource;
import com.kbc.foodie.contract.mealdb.MealDBSearchResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mealdb-client", url = "https://www.themealdb.com/api/json/v1/1/")
@Component
public interface TheMealDBClient {

    @GetMapping("/search.php")
    ResponseEntity<MealDBSearchResource> search(@RequestParam(name = "s") String search);

    @GetMapping("/lookup.php")
    ResponseEntity<MealDBSearchResource> getRecipe(@RequestParam(name = "i") String id);
}
