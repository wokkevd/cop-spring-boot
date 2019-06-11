package com.kbc.foodie.controller;

import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/recipes", produces = "application/json")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeResource> getRecipe(@PathVariable String recipeId) {
        return recipeService.getRecipe(recipeId)
                .map(recipeResource -> ResponseEntity.ok().body(recipeResource))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RecipeResource>> searchRecipe(@RequestParam String search) {
        return ResponseEntity.ok().body(recipeService.search(search));
    }
}
