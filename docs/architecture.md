# Smart Fridge — Architecture & System Design

## System Architecture

The application follows a layered architecture:

- **Angular frontend** — dashboard, forms, and auth pages. Never talks to the database or AI API directly.
- **Spring Boot backend** — exposes a REST API, enforces JWT-based security, and is the only layer that talks to the database and the external AI API.
- **PostgreSQL database** — stores users and ingredients.
- **AI API (OpenAI / Gemini)** — called by the backend to generate recipe suggestions based on the user's current ingredients.

This layering keeps the AI API key and database credentials safely on the server, never exposed to the browser, and ensures all data access goes through the backend's security checks.

## Database Schema (ERD)

**User**
| Field | Type | Notes |
|---|---|---|
| id | Long (PK) | |
| username | String | Unique |
| email | String | Unique |
| password | String | BCrypt-hashed, never stored in plain text |

**Ingredient**
| Field | Type | Notes |
|---|---|---|
| id | Long (PK) | |
| user_id | Long (FK → User.id) | Links each ingredient to its owner |
| name | String | |
| quantity | Double | |
| unit | String | |

**Relationship:** One `User` owns many `Ingredient` records (one-to-many). Every ingredient query is scoped to `user_id`, so users only ever see their own fridge contents.

We deliberately do not store recipe suggestions in the database for MVP — they're generated live from the AI API on each request. Recipe history/storage is a documented future enhancement, not built now.

## API Design

| Method | Endpoint | Purpose | Auth required? |
|---|---|---|---|
| POST | `/api/auth/register` | Create a new user | No |
| POST | `/api/auth/login` | Authenticate, return JWT | No |
| GET | `/api/ingredients` | Get logged-in user's ingredients | Yes |
| POST | `/api/ingredients` | Add a new ingredient | Yes |
| PUT | `/api/ingredients/{id}` | Edit an ingredient | Yes |
| DELETE | `/api/ingredients/{id}` | Remove an ingredient | Yes |
| POST | `/api/ingredients/suggest-recipe` | Send current ingredients to AI, get recipe suggestions | Yes |

All endpoints except `register` and `login` require a valid JWT, passed via the `Authorization: Bearer <token>` header.

`suggest-recipe` is a POST (not GET) because it triggers an external computation (the AI call) rather than simple data retrieval — consistent with REST conventions for action-triggering endpoints.

## Tech Stack Justification

- **Spring Boot 3.x / Java 17** — industry-standard for enterprise Java REST APIs; Spring Boot 3.x requires Java 17+, keeping the stack current.
- **PostgreSQL** — relational data (users own ingredients) fits a relational model better than NoSQL alternatives.
- **JWT authentication** — stateless; no server-side session storage needed, which scales better than traditional session-based auth.
- **Angular 17** — matches existing frontend experience; strong typing via TypeScript.
- **Docker + Docker Compose** — ensures consistent environment across development and deployment.
- **GitHub Actions** — automates build, test, and containerization on every push.

---
*Part of the Smart Fridge project documentation. See the root README.md for project overview and setup instructions.*
