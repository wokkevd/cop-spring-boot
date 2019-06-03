package com.kbc.foodie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String recipeId;
    private String name;
    private String category;
    private String origin;
    private String instructions;
    private String picture;
    private String video;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "recipeEntity")
    private List<IngredientEntity> ingredientEntities;
}
