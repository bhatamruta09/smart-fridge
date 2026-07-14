# Smart Fridge — AI-Powered Meal Planner

## Problem Statement
People often don't know what to cook with the ingredients they already have, leading to food waste and repetitive meals. Smart Fridge helps users track their available ingredients and get AI-generated recipe suggestions based on what's actually in their kitchen.

## Features

### MVP (Current Scope)
- **User Authentication** — secure registration and login (JWT-based), so each user's fridge data is private
- **Ingredient Management (CRUD)** — add, view, edit, and remove ingredients (name, quantity, unit)
- **AI Recipe Suggestions** — click "What can I cook?" to get AI-generated recipe suggestions based on current ingredients, including ingredients used and simple preparation steps

### Future Enhancements (Planned, Not Yet Built)
- Expiry date tracking with alerts for ingredients nearing expiry
- Meal planning calendar
- Shopping list auto-generation for missing recipe ingredients

## User Stories
1. As a user, I want to register and log in, so my fridge data is private to me
2. As a user, I want to add an ingredient to my fridge list
3. As a user, I want to view all my current ingredients in one dashboard
4. As a user, I want to edit or remove an ingredient when I use it up or buy more
5. As a user, I want to click "What can I cook?" and get AI-generated recipe suggestions based on my current ingredients
6. As a user, I want to see the suggested recipes clearly, with ingredients used and simple steps

## Non-Functional Requirements
- **Security:** Passwords are hashed (BCrypt); all ingredient endpoints are protected via JWT authentication; API keys (e.g. for the AI service) are never exposed to the frontend
- **Usability:** Simple, clean dashboard with clear feedback on user actions
- **Performance:** AI recipe generation may take a few seconds — the UI shows a loading state rather than freezing
- **Deployment:** Publicly accessible via a live deployed URL; fully containerized with Docker for portability

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.x, Spring Security, Spring Data JPA
- **Frontend:** Angular 17
- **Database:** PostgreSQL
- **AI Integration:** OpenAI / Gemini API
- **Containerization:** Docker, Docker Compose
- **CI/CD:** GitHub Actions
- **Testing:** JUnit, Mockito

## Architecture
See [docs/architecture.md](docs/architecture.md) for the full system architecture, database schema (ERD), and API design.

## Ports
To avoid conflicts with other local projects, Smart Fridge uses non-default ports:

| Service | Port |
|---|---|
| Backend (Spring Boot) | 8081 |
| Frontend (Angular) | 4201 |
| PostgreSQL | 5433 |

## Getting Started

### Backend
1. Open `smart-fridge-backend` in Eclipse as an existing Maven project
2. Ensure Docker Desktop is running (PostgreSQL is started automatically via Docker Compose)
3. Run `SmartFridgeBackendApplication.java` as a Spring Boot App
4. Backend runs at `http://localhost:8081`

### Frontend
1. Open `smart-fridge-frontend` in VS Code
2. Run `npm install`
3. Run `ng serve` (port 4201 is pre-configured in `angular.json`)
4. Frontend runs at `http://localhost:4201`

## API Documentation
*(To be added as endpoints are implemented)*

## Testing
*(To be added in the testing phase — unit and integration test coverage)*

## Screenshots
*(To be added once the dashboard UI is built)*

## Author
Amruta Bhat
