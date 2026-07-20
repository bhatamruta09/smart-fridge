package com.springboot.smart_fridge_backend.service;

import com.springboot.smart_fridge_backend.model.Ingredient;
import com.springboot.smart_fridge_backend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient updated) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setUnit(updated.getUnit());
        return ingredientRepository.save(existing);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}