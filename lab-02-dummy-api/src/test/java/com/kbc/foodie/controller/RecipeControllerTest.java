package com.kbc.foodie.controller;

import com.kbc.foodie.contract.IngredientResource;
import com.kbc.foodie.contract.RecipeResource;
import com.kbc.foodie.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    private static final String RECIPE_1_ID = "12345";
    private static final String RECIPE_1_NAME = "Hot Chicken Tikka Masala";
    private static final String RECIPE_1_ORIGIN = "Indian";
    private static final String RECIPE_1_PICTURE_URL = "https://image.shutterstock.com/z/stock-photo-traditional-indian-british-dish-chicken-tikka-masala-background-spicy-chicken-tikka-masala-curry-695932900.jpg";
    private static final String RECIPE_1_INSTRUCTIONS = "Put ingredients together. Make the food. Serve hot. Provide bread for those who can't handle hot food.";
    private static final String RECIPE_1_INGREDIENT_1_QUANTITY = "Some";
    private static final String RECIPE_1_INGREDIENT_1_NAME = "Chicken";
    private static final String RECIPE_1_INGREDIENT_2_QUANTITY = "A dash";
    private static final String RECIPE_1_INGREDIENT_2_NAME = "All kinds of spices";
    private static final String RECIPE_1_INGREDIENT_3_QUANTITY = "Loads";
    private static final String RECIPE_1_INGREDIENT_3_NAME = "Everything else";

    private static final String RECIPE_2_ID = "98765";
    private static final String RECIPE_2_NAME = "Hot Ma Po Tofu";
    private static final String RECIPE_2_ORIGIN = "Chinese";
    private static final String RECIPE_2_PICTURE_URL = "https://thewoksoflife.com/wp-content/uploads/2014/03/Mapo-tofu-65.jpg";
    private static final String RECIPE_2_INSTRUCTIONS = "Put ingredients in hot pot. Do stuff. Serve hot. Provide rice for those who can't handle hot food.";
    private static final String RECIPE_2_INGREDIENT_1_QUANTITY = "A bunch";
    private static final String RECIPE_2_INGREDIENT_1_NAME = "Tofu";
    private static final String RECIPE_2_INGREDIENT_2_QUANTITY = "A bag";
    private static final String RECIPE_2_INGREDIENT_2_NAME = "Hot pot spices";
    private static final String RECIPE_2_INGREDIENT_3_QUANTITY = "Loads";
    private static final String RECIPE_2_INGREDIENT_3_NAME = "Everything else";

    private static final String NON_EXISTING_RECIPE_ID = "ABCDE";
    private static final String CATEGORY_SPICY = "Spicy";
    private static final String SEARCH_HOT = "Hot";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService theMealDBService;

    @Before
    public void init() {
        RecipeResource recipeResource1 = RecipeResource.builder()
                .recipeId(RECIPE_1_ID)
                .name(RECIPE_1_NAME)
                .category(CATEGORY_SPICY)
                .origin(RECIPE_1_ORIGIN)
                .picture(RECIPE_1_PICTURE_URL)
                .instructions(RECIPE_1_INSTRUCTIONS)
                .ingredients(Arrays.asList(
                        new IngredientResource(RECIPE_1_INGREDIENT_1_QUANTITY, RECIPE_1_INGREDIENT_1_NAME),
                        new IngredientResource(RECIPE_1_INGREDIENT_2_QUANTITY, RECIPE_1_INGREDIENT_2_NAME),
                        new IngredientResource(RECIPE_1_INGREDIENT_3_QUANTITY, RECIPE_1_INGREDIENT_3_NAME)
                ))
                .build();
        RecipeResource recipeResource2 = RecipeResource.builder()
                .recipeId(RECIPE_2_ID)
                .name(RECIPE_2_NAME)
                .category(CATEGORY_SPICY)
                .origin(RECIPE_2_ORIGIN)
                .picture(RECIPE_2_PICTURE_URL)
                .instructions(RECIPE_2_INSTRUCTIONS)
                .ingredients(Arrays.asList(
                        new IngredientResource(RECIPE_2_INGREDIENT_1_QUANTITY, RECIPE_2_INGREDIENT_1_NAME),
                        new IngredientResource(RECIPE_2_INGREDIENT_2_QUANTITY, RECIPE_2_INGREDIENT_2_NAME),
                        new IngredientResource(RECIPE_2_INGREDIENT_3_QUANTITY, RECIPE_2_INGREDIENT_3_NAME)
                ))
                .build();

        when(theMealDBService.getRecipe(RECIPE_1_ID)).thenReturn(Optional.of(recipeResource1));
        when(theMealDBService.getRecipe(RECIPE_2_ID)).thenReturn(Optional.of(recipeResource2));
        when(theMealDBService.search(SEARCH_HOT)).thenReturn(Arrays.asList(recipeResource1, recipeResource2));
    }

    @Test
    public void getRecipeExistingId() throws Exception {
        mockMvc.perform(get("/recipes/" + RECIPE_1_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeId", is(RECIPE_1_ID)))
                .andExpect(jsonPath("$.name", is(RECIPE_1_NAME)))
                .andExpect(jsonPath("$.category", is(CATEGORY_SPICY)))
                .andExpect(jsonPath("$.origin", is(RECIPE_1_ORIGIN)))
                .andExpect(jsonPath("$.instructions", is(RECIPE_1_INSTRUCTIONS)))
                .andExpect(jsonPath("$.picture", is(RECIPE_1_PICTURE_URL)))
                .andExpect(jsonPath("$.ingredients", hasSize(3)))
                .andExpect(jsonPath("$.ingredients.[0].quantity", is(RECIPE_1_INGREDIENT_1_QUANTITY)))
                .andExpect(jsonPath("$.ingredients.[0].name", is(RECIPE_1_INGREDIENT_1_NAME)))
                .andExpect(jsonPath("$.ingredients.[1].quantity", is(RECIPE_1_INGREDIENT_2_QUANTITY)))
                .andExpect(jsonPath("$.ingredients.[1].name", is(RECIPE_1_INGREDIENT_2_NAME)))
                .andExpect(jsonPath("$.ingredients.[2].quantity", is(RECIPE_1_INGREDIENT_3_QUANTITY)))
                .andExpect(jsonPath("$.ingredients.[2].name", is(RECIPE_1_INGREDIENT_3_NAME)));
    }

    @Test
    public void getRecipeNonExistingId() throws Exception {
        mockMvc.perform(get("/recipes/" + NON_EXISTING_RECIPE_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchRecipe() throws Exception {
        mockMvc.perform(get("/recipes")
                .param("search", SEARCH_HOT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].recipeId", is(RECIPE_1_ID)))
                .andExpect(jsonPath("$.[0].name", is(RECIPE_1_NAME)))
                .andExpect(jsonPath("$.[0].category", is(CATEGORY_SPICY)))
                .andExpect(jsonPath("$.[0].origin", is(RECIPE_1_ORIGIN)))
                .andExpect(jsonPath("$.[0].instructions", is(RECIPE_1_INSTRUCTIONS)))
                .andExpect(jsonPath("$.[0].picture", is(RECIPE_1_PICTURE_URL)))
                .andExpect(jsonPath("$.[0].ingredients", hasSize(3)))
                .andExpect(jsonPath("$.[0].ingredients.[0].quantity", is(RECIPE_1_INGREDIENT_1_QUANTITY)))
                .andExpect(jsonPath("$.[0].ingredients.[0].name", is(RECIPE_1_INGREDIENT_1_NAME)))
                .andExpect(jsonPath("$.[0].ingredients.[1].quantity", is(RECIPE_1_INGREDIENT_2_QUANTITY)))
                .andExpect(jsonPath("$.[0].ingredients.[1].name", is(RECIPE_1_INGREDIENT_2_NAME)))
                .andExpect(jsonPath("$.[0].ingredients.[2].quantity", is(RECIPE_1_INGREDIENT_3_QUANTITY)))
                .andExpect(jsonPath("$.[0].ingredients.[2].name", is(RECIPE_1_INGREDIENT_3_NAME)))
                .andExpect(jsonPath("$.[1].recipeId", is(RECIPE_2_ID)))
                .andExpect(jsonPath("$.[1].name", is(RECIPE_2_NAME)))
                .andExpect(jsonPath("$.[1].category", is(CATEGORY_SPICY)))
                .andExpect(jsonPath("$.[1].origin", is(RECIPE_2_ORIGIN)))
                .andExpect(jsonPath("$.[1].instructions", is(RECIPE_2_INSTRUCTIONS)))
                .andExpect(jsonPath("$.[1].picture", is(RECIPE_2_PICTURE_URL)))
                .andExpect(jsonPath("$.[1].ingredients", hasSize(3)))
                .andExpect(jsonPath("$.[1].ingredients.[0].quantity", is(RECIPE_2_INGREDIENT_1_QUANTITY)))
                .andExpect(jsonPath("$.[1].ingredients.[0].name", is(RECIPE_2_INGREDIENT_1_NAME)))
                .andExpect(jsonPath("$.[1].ingredients.[1].quantity", is(RECIPE_2_INGREDIENT_2_QUANTITY)))
                .andExpect(jsonPath("$.[1].ingredients.[1].name", is(RECIPE_2_INGREDIENT_2_NAME)))
                .andExpect(jsonPath("$.[1].ingredients.[2].quantity", is(RECIPE_2_INGREDIENT_3_QUANTITY)))
                .andExpect(jsonPath("$.[1].ingredients.[2].name", is(RECIPE_2_INGREDIENT_3_NAME)));
    }

    @Test
    public void searchRecipeNoResults() throws Exception {
        mockMvc.perform(get("/recipes")
                .param("search", "Earwax"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}