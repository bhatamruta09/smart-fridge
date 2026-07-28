package com.springboot.smart_fridge_backend.repository;

import com.springboot.smart_fridge_backend.model.Ingredient;
import com.springboot.smart_fridge_backend.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	List<Ingredient> findByUser(User user);
}
