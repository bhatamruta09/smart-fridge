import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Ingredient } from '../../models/ingredient.model';
import { IngredientService } from "../../services/ingredient.service";
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { marked } from 'marked';

@Component({
  selector: 'app-ingredient-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ingredient-list.component.html',
  styleUrl: './ingredient-list.component.css'
})

export class IngredientListComponent implements OnInit {
  ingredients: Ingredient[] = [];
  newIngredient: Ingredient = { name: '', quantity: 0, unit: '' };
  editingId: number | null = null;
  editIngredient: Ingredient = { name: '', quantity: 0, unit: '' };
  username: string | null = null;
  recipeSuggestion: string = '';
  isLoadingRecipe: boolean = false;
  recipeError: string = '';

  constructor(private ingredientService: IngredientService,
    private authService: AuthService,
    private router: Router) {}

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.loadIngredients();
  }

  loadIngredients(): void {
    this.ingredientService.getAllIngredients().subscribe({
      next: (data) => { this.ingredients = data; },
      error: (err) => { console.error('Failed to load ingredients:', err); }
    });
  }

  onAddIngredient(): void {
    this.ingredientService.addIngredient(this.newIngredient).subscribe({
      next: (savedIngredient) => {
        this.ingredients.push(savedIngredient);
        this.newIngredient = { name: '', quantity: 0, unit: '' };
      },
      error: (err) => { console.error('Failed to add ingredient:', err); }
    });
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  startEdit(ingredient: Ingredient): void {
    this.editingId = ingredient.id!;
    this.editIngredient = { ...ingredient };
  }

  cancelEdit(): void {
    this.editingId = null;
  }

  saveEdit(id: number): void {
    this.ingredientService.updateIngredient(id, this.editIngredient).subscribe({
      next: (updated) => {
        const index = this.ingredients.findIndex(i => i.id === id);
        this.ingredients[index] = updated;
        this.editingId = null;
      },
      error: (err) => { console.error('Failed to update ingredient:', err); }
    });
  }

  deleteIngredient(id: number): void {
    if (!confirm('Remove this ingredient?')) return;

    this.ingredientService.deleteIngredient(id).subscribe({
      next: () => {
        this.ingredients = this.ingredients.filter(i => i.id !== id);
      },
      error: (err) => { console.error('Failed to delete ingredient:', err); }
    });
  }

  onSuggestRecipe(): void {
    this.isLoadingRecipe = true;
    this.recipeSuggestion = '';
    this.recipeError = '';

    this.ingredientService.suggestRecipe().subscribe({
      next: (response) => {
        this.recipeSuggestion = marked.parse(response.suggestion) as string;
        this.isLoadingRecipe = false;
      },
      error: (err) => {
        console.error('Failed to get recipe suggestion:', err);
        this.recipeError = 'Could not get a recipe suggestion right now. Please try again in a moment.';
        this.isLoadingRecipe = false;
      }
    });
  }
}