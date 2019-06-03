package com.kbc.foodie.repository;

import com.kbc.foodie.domain.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<RecipeEntity, UUID> {

    Optional<RecipeEntity> findByRecipeId(String recipeId);
}
