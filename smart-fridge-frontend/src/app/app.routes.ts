import { Routes } from '@angular/router';
import { IngredientListComponent } from './components/ingredient-list/ingredient-list.component';

export const routes: Routes = [
  { path: '', component: IngredientListComponent },
  { path: 'ingredients', component: IngredientListComponent }
];