package com.kbc.starwars.client;

import com.kbc.starwars.contract.mealdb.MealDBRecipeResource;
import com.kbc.starwars.contract.mealdb.MealDBSearchResource;
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
    ResponseEntity<MealDBRecipeResource> getRecipe(@RequestParam(name = "i") String id);
}
