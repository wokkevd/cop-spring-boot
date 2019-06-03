package com.kbc.foodie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String quantity;
    private String name;

    @ManyToOne
    @JoinColumn(name = "recipe_fk", nullable = false, updatable = false)
    private RecipeEntity recipeEntity;

    public IngredientEntity(String quantity, String name, RecipeEntity recipeEntity) {
        this.quantity = quantity;
        this.name = name;
        this.recipeEntity = recipeEntity;
    }
}
