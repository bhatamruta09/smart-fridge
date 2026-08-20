package com.springboot.smart_fridge_backend.controller;

import com.springboot.smart_fridge_backend.model.Ingredient;
import com.springboot.smart_fridge_backend.service.AiService;
import com.springboot.smart_fridge_backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = "http://localhost:4201")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    
    @Autowired
    private AiService aiService;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
    }
    
    @PostMapping("/suggest-recipe")
    public Map<String, String> suggestRecipe() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        String suggestion = aiService.suggestRecipes(ingredients);
        return Map.of("suggestion", suggestion);
    }
}