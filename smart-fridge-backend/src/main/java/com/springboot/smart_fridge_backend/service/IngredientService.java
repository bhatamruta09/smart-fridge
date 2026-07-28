package com.springboot.smart_fridge_backend.service;

import com.springboot.smart_fridge_backend.model.Ingredient;
import com.springboot.smart_fridge_backend.model.User;
import com.springboot.smart_fridge_backend.repository.IngredientRepository;
import com.springboot.smart_fridge_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Ingredient> getAllIngredients() {
        User currentUser = getCurrentUser();
        return ingredientRepository.findByUser(currentUser);
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        ingredient.setUser(getCurrentUser());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient updated) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        if (!existing.getUser().getId().equals(getCurrentUser().getId())) {
            throw new RuntimeException("Not authorized to update this ingredient");
        }

        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setUnit(updated.getUnit());
        return ingredientRepository.save(existing);
    }

    public void deleteIngredient(Long id) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        if (!existing.getUser().getId().equals(getCurrentUser().getId())) {
            throw new RuntimeException("Not authorized to delete this ingredient");
        }

        ingredientRepository.deleteById(id);
    }
}