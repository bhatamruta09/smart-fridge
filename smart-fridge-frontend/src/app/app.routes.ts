import { Routes } from '@angular/router';
import { IngredientListComponent } from './components/ingredient-list/ingredient-list.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'ingredients', component: IngredientListComponent, canActivate: [authGuard] }
];